package com.unifor.MedMaisFacil.service;

import com.unifor.MedMaisFacil.enums.PrioridadeChamado;
import com.unifor.MedMaisFacil.enums.SintomaPrincipal;
import com.unifor.MedMaisFacil.enums.StatusChamado;
import com.unifor.MedMaisFacil.model.*;
import com.unifor.MedMaisFacil.model.classificacao.AvaliacaoSinaisVitais;
import com.unifor.MedMaisFacil.utils.MetodosUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TotemServiceImpl implements TotemService {

    private final PacienteService pacienteService;

    private final ChamadoService chamadoService;

    private final SinaisVitaisService sinaisVitaisService;

    private final SenhaFilaService senhaFilaService;

    private final UnidadeSaudeService unidadeSaudeService;

    @Override
    public TotemIdentificacao identificarPaciente(String cpf) {
        Paciente pacienteEncontrado = pacienteService.buscarPacienteByCpf(cpf);

        chamadoService.expirarChamadosAntigosNaoUrgentes(pacienteEncontrado.getId());

        List<Chamado> chamadosPendentesDoPaciente = chamadoService.buscarChamadoByPacienteIdAndStatus(pacienteEncontrado.getId());

        List<ChamadoPendente> chamadosPendentes = chamadosPendentesDoPaciente.stream()
                .map(chamado -> ChamadoPendente.builder()
                        .chamadoId(chamado.getId())
                        .sintomaPrincipal(chamado.getQuestionarioSintomas().getSintomaPrincipal())
                        .dataCriacao(chamado.getDataCriacao())
                        .prioridadeChamado(chamado.getPrioridadeChamado())
                        .build())
                .toList();

        int idade = MetodosUtil.calcularIdade(pacienteEncontrado.getDataNascimento());

        return new TotemIdentificacao(pacienteEncontrado.getId(), pacienteEncontrado.getNome(), idade, chamadosPendentes);
    }

    @Override
    @Transactional
    public SinaisVitais registrarSinaisVitais(Long chamadoId, SinaisVitais sinaisVitais) {
        Chamado chamadoIdentificado = chamadoService.buscarChamadoById(chamadoId);

        if (chamadoIdentificado.getStatusChamado() != StatusChamado.AGUARDANDO_TRIAGEM) {
            throw new IllegalStateException("Este chamado não está aguardando triagem (status atual: " + chamadoIdentificado.getStatusChamado() + ")");
        }

        if (chamadoIdentificado.getSinaisVitais() != null) {
            sinaisVitais.setId(chamadoIdentificado.getSinaisVitais().getId());
        }

        Chamado chamadoAtualizado = recalcularPrioridade(chamadoIdentificado, sinaisVitais);

        SinaisVitais sinaisVitaisParaSalvar = sinaisVitais.toBuilder()
                .chamado(chamadoAtualizado)
                .capturadoEm(LocalDateTime.now())
                .build();

        return sinaisVitaisService.salvarSinaisVitais(sinaisVitaisParaSalvar);
    }

    @Override
    @Transactional
    public TotemCheckin finalizarCheckIn(Long chamadoId, Long unidadeSaudeId) {
        Chamado chamadoIdentificado = chamadoService.buscarChamadoById(chamadoId);

        if (chamadoIdentificado.getStatusChamado() != StatusChamado.AGUARDANDO_TRIAGEM) {
            throw new IllegalStateException("Chamado não está aguardando triagem (status atual:  " + chamadoIdentificado.getStatusChamado() + ")");
        }

        if (chamadoIdentificado.getSinaisVitais() == null) {
            throw new IllegalStateException("Sinais vitais ainda não foram capturados para este chamado");
        }

        UnidadeSaude unidadeDoTotem = unidadeSaudeService.buscarUnidadeSaudeById(unidadeSaudeId);

        SintomaPrincipal sintoma = chamadoIdentificado.getQuestionarioSintomas().getSintomaPrincipal();
        if (unidadeDoTotem.getSintomasAtendidos() == null || !unidadeDoTotem.getSintomasAtendidos().contains(sintoma)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Esta unidade não atende o sintoma informado. Procure a recepção.");
        }

        String senhaGerada = senhaFilaService.gerarSenhaFila(chamadoIdentificado.getPrioridadeChamado());

        Chamado chamadoComAlteracoesSalvas = chamadoService.salvarAlteracoes(chamadoIdentificado.toBuilder()
                .statusChamado(StatusChamado.EM_FILA)
                .senhaFila(senhaGerada)
                .unidadeSaude(unidadeDoTotem)
                .dataCheckin(LocalDateTime.now())
                .dataSenha(LocalDate.now())
                .build());

        return new TotemCheckin(
                chamadoComAlteracoesSalvas.getSenhaFila(),
                chamadoComAlteracoesSalvas.getPrioridadeChamado()
        );
    }

    private Chamado recalcularPrioridade (Chamado chamado, SinaisVitais sinais) {
        Paciente pacienteConsultado = pacienteService.buscarPacienteById(chamado.getPaciente().getId());

        if (MetodosUtil.calcularIdade(pacienteConsultado.getDataNascimento()) < 18) {
            return chamado;
        }

        SintomaPrincipal sintomaPrincipal = chamado.getQuestionarioSintomas().getSintomaPrincipal();

        PrioridadeChamado minima = AvaliacaoSinaisVitais.prioridadeMinima(sinais, sintomaPrincipal);
        PrioridadeChamado nova = AvaliacaoSinaisVitais.escalar(chamado.getPrioridadeChamado(), minima);

        if (nova == chamado.getPrioridadeChamado()) {
            return chamado;
        }

        return chamadoService.salvarAlteracoes(chamado.toBuilder().prioridadeChamado(nova).build());
    }
}
