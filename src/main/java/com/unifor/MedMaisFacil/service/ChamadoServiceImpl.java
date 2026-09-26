package com.unifor.MedMaisFacil.service;

import com.unifor.MedMaisFacil.entity.RespostasQuestionario;
import com.unifor.MedMaisFacil.enums.PrioridadeChamado;
import com.unifor.MedMaisFacil.enums.StatusChamado;
import com.unifor.MedMaisFacil.exceptions.ChamadoNotFoundException;
import com.unifor.MedMaisFacil.mapper.ChamadoMapper;
import com.unifor.MedMaisFacil.model.*;
import com.unifor.MedMaisFacil.model.classificacao.ProtocoloManchester;
import com.unifor.MedMaisFacil.repository.ChamadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChamadoServiceImpl implements ChamadoService {

    private final ChamadoRepository chamadoRepository;
    private final ChamadoMapper chamadoMapper;

    private final PacienteService pacienteService;

    private final OrientacaoService orientacaoService;

    private final UnidadeSaudeService unidadeSaudeService;

    @Override
    @Transactional
    public Chamado criarChamado(Long pacienteId, Chamado chamado) {
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

    private UnidadeSaude buscarUnidadeSaudeRecomendada(Chamado chamado) {
        if (chamado.getSintomaPrincipal() == null || chamado.getLatitudeAtual() == null || chamado.getLongitudeAtual() == null) {
            return null;
        }

        return unidadeSaudeService.buscarUnidadeSaudeMaisProxima(
                chamado.getSintomaPrincipal(),
                chamado.getLatitudeAtual(),
                chamado.getLongitudeAtual()
        ).orElse(null);
    }

}
