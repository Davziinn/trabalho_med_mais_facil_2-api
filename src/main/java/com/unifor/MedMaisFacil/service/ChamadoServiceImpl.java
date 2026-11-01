package com.unifor.MedMaisFacil.service;

import com.unifor.MedMaisFacil.entity.RespostasQuestionario;
import com.unifor.MedMaisFacil.enums.PrioridadeChamado;
import com.unifor.MedMaisFacil.enums.StatusChamado;
import com.unifor.MedMaisFacil.exceptions.ChamadoJaAbertoException;
import com.unifor.MedMaisFacil.exceptions.ChamadoNotFoundException;
import com.unifor.MedMaisFacil.mapper.ChamadoMapper;
import com.unifor.MedMaisFacil.model.*;
import com.unifor.MedMaisFacil.model.classificacao.ProtocoloManchester;
import com.unifor.MedMaisFacil.repository.ChamadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ChamadoServiceImpl implements ChamadoService {

    @Value("${medmaisfacil.lotacao.janela-horas:3}")
    private long janelaHoras;

    private final ChamadoRepository chamadoRepository;
    private final ChamadoMapper chamadoMapper;

    private final PacienteService pacienteService;

    private final OrientacaoService orientacaoService;

    private final UnidadeSaudeService unidadeSaudeService;

    private static final List<PrioridadeChamado> CORES_NAO_URGENTES = List.of(PrioridadeChamado.VERDE, PrioridadeChamado.AZUL);

    private static final Set<StatusChamado> NAO_CANCELAVEIS = EnumSet.of(
            StatusChamado.CANCELADO,
            StatusChamado.EXPIRADO
    );

    @Override
    @Transactional
    public Chamado criarChamado(Long pacienteId, Chamado chamado) {

        expirarChamadosAntigosNaoUrgentes(pacienteId);

        List<Chamado> chamadosAbertos = chamadoRepository.findByPaciente_IdAndStatusChamado(pacienteId, StatusChamado.AGUARDANDO_TRIAGEM).stream().map(chamadoMapper::toModel).toList();

        if (!chamadosAbertos.isEmpty()) {
            if (!chamado.isConfirmarNovoAtendimento()) {
                throw new ChamadoJaAbertoException(chamadosAbertos.getFirst().getPrioridadeChamado());
            }
            deletarTudo(chamadosAbertos);
        }

        Paciente pacienteEncontrado = pacienteService.buscarPacienteById(pacienteId);

        RespostasQuestionario respostas = new RespostasQuestionario(
                chamado.getDiscriminadoresGerais(),
                chamado.getRespostasFluxograma()
        );

        QuestionarioSintomas questionario = QuestionarioSintomas.builder()
                .sintomaPrincipal(chamado.getSintomaPrincipal())
                .respostas(respostas)
                .build();

        PrioridadeChamado prioridadeCor = ProtocoloManchester.classificar(
                chamado.getDiscriminadoresGerais(),
                chamado.getSintomaPrincipal(),
                chamado.getRespostasFluxograma()
        );

        Orientacao orientacaoMedica = orientacaoService.buscarOrientacao(chamado.getSintomaPrincipal(), prioridadeCor);
        UnidadeSaude unidadeSaudeRecomendada = buscarUnidadeSaudeRecomendada(chamado);

        Chamado chamadoCriado = Chamado.builder()
                .id(chamado.getId())
                .statusChamado(StatusChamado.AGUARDANDO_TRIAGEM)
                .paciente(pacienteEncontrado)
                .questionarioSintomas(questionario)
                .dataCriacao(chamado.getDataCriacao())
                .prioridadeChamado(prioridadeCor)
                .orientacao(orientacaoMedica)
                .unidadeSaude(unidadeSaudeRecomendada)
                .build();

        Chamado chamadoSalvo = chamadoMapper.toModel(chamadoRepository.save(chamadoMapper.toEntity(chamadoCriado)));
        return chamadoSalvo.toBuilder()
                .orientacao(orientacaoMedica)
                .unidadeSaude(unidadeSaudeRecomendada)
                .build();
    }

    @Override
    public List<Chamado> buscarChamadoByPacienteIdAndStatus(Long pacienteId) {
        return chamadoRepository.findByPaciente_IdAndStatusChamado(pacienteId, StatusChamado.AGUARDANDO_TRIAGEM).stream()
                .map(chamadoMapper::toModel)
                .toList();
    }

    @Override
    public Chamado buscarChamadoById(Long chamadoId) {
        return chamadoMapper.toModel(chamadoRepository.findById(chamadoId).orElseThrow(
                () -> new ChamadoNotFoundException("Chamado não encontrado")
        ));
    }

    @Override
    public Chamado salvarAlteracoes(Chamado dadosChamado) {
        return chamadoMapper.toModel(chamadoRepository.save(chamadoMapper.toEntity(dadosChamado)));
    }

    @Override
    public void deletarTudo(List<Chamado> chamado) {
        chamadoRepository.deleteAllById(chamado.stream().map(Chamado::getId).toList());
        chamadoRepository.flush();
    }

    @Override
    public void expirarChamadosAntigosNaoUrgentes (Long pacienteId) {
        LocalDateTime limite24horas = LocalDateTime.now().minusHours(24);

        List<Chamado> expirados = chamadoRepository.findByPaciente_IdAndStatusChamadoAndPrioridadeChamadoInAndDataCriacaoBefore(pacienteId, StatusChamado.AGUARDANDO_TRIAGEM, CORES_NAO_URGENTES, limite24horas)
                .stream().map(chamadoMapper::toModel)
                .toList();

        expirados.forEach(chamado -> chamado.setStatusChamado(StatusChamado.EXPIRADO));
        chamadoRepository.saveAll(expirados.stream().map(chamadoMapper::toEntity).toList());
    }

    @Override
    public List<Chamado> buscarPacienteByIdEByStatusOrdenandoByDataDecrescente(Long pacienteId, List<StatusChamado> status) {
        return chamadoRepository.findByPaciente_IdAndStatusChamadoInOrderByDataCriacaoDesc(pacienteId, status).stream().map(chamadoMapper::toModel).toList();
    }

    @Override
    public long contarChamadosAtivosApos(Long unidadeId, StatusChamado status, LocalDateTime apos) {
        return chamadoRepository.countByUnidadeSaude_IdAndStatusChamadoAndDataCheckinAfter(unidadeId, status, apos);
    }

    @Override
    public void cancelar(Long pacienteId, Long chamadoId) {
        Chamado chamadoEncontrado = buscarChamadoById(chamadoId);

        if (!chamadoEncontrado.getPaciente().getId().equals(pacienteId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Chamado não encontrado.");
        }

        if (NAO_CANCELAVEIS.contains(chamadoEncontrado.getStatusChamado())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "Este chamado não pode mais ser cancelado.");
        }

        Chamado cancelado = chamadoEncontrado.toBuilder()
                .statusChamado(StatusChamado.CANCELADO)
                .build();

        chamadoRepository.save(chamadoMapper.toEntity(cancelado));
    }

    private UnidadeSaude buscarUnidadeSaudeRecomendada(Chamado chamado) {
        if (chamado.getSintomaPrincipal() == null || chamado.getLatitudeAtual() == null || chamado.getLongitudeAtual() == null) {
            return null;
        }

        return unidadeSaudeService.buscarUnidadeSaudeMaisProxima(
                chamado.getSintomaPrincipal(),
                chamado.getLatitudeAtual(),
                chamado.getLongitudeAtual()
        ).map(unidade -> unidade.toBuilder().lotado(estaLotado(unidade)).build())
                .orElse(null);
    }

    private boolean estaLotado (UnidadeSaude unidadeSaude) {
        if (unidadeSaude == null) {
            return false;
        }

        LocalDateTime inicioJanela = LocalDateTime.now().minusHours(janelaHoras);
        long quantidadePacientesNaFila = contarChamadosAtivosApos(unidadeSaude.getId(), StatusChamado.EM_FILA, inicioJanela);

        return quantidadePacientesNaFila >= unidadeSaude.getLimiteLotacao();
    }

}
