package com.unifor.MedMaisFacil.service;

import com.unifor.MedMaisFacil.enums.StatusChamado;
import com.unifor.MedMaisFacil.model.*;
import com.unifor.MedMaisFacil.utils.MetodosUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TotemServiceImpl implements TotemService {

    private final PacienteService pacienteService;

    private final ChamadoService chamadoService;

    private final SinaisVitaisService sinaisVitaisService;

    private final SenhaFilaService senhaFilaService;

    @Override
    public TotemIdentificacao identificarPaciente(String cpf) {
        Paciente pacienteEncontrado = pacienteService.buscarPacienteByCpf(cpf);
        List<Chamado> chamadosPendentesDoPaciente = chamadoService.buscarChamadoByPacienteIdAndStatus(pacienteEncontrado.getId());

        List<ChamadoPendente> chamadosPendentes = chamadosPendentesDoPaciente.stream()
                .map(chamado -> ChamadoPendente.builder()
                        .chamadoId(chamado.getId())
                        .sintomaPrincipal(chamado.getQuestionarioSintomas().getSintomaPrincipal())
                        .dataCriacao(chamado.getDataCriacao())
                        .build())
                .toList();

        int idade = MetodosUtil.calcularIdade(pacienteEncontrado.getDataNascimento());

        return new TotemIdentificacao(pacienteEncontrado.getNome(), idade, chamadosPendentes);
    }

    @Override
    public SinaisVitais registrarSinaisVitais(Long chamadoId, SinaisVitais sinaisVitais) {
        Chamado chamadoIdentificado = chamadoService.buscarChamadoById(chamadoId);

        if (chamadoIdentificado.getStatusChamado() != StatusChamado.AGUARDANDO_TRIAGEM) {
            throw new IllegalStateException("Este chamado não está aguardando triagem (status atual: " + chamadoIdentificado.getStatusChamado() + ")");
        }

        SinaisVitais sinaisVitaisParaSalvar = sinaisVitais.toBuilder()
                .chamado(chamadoIdentificado)
                .build();



        return sinaisVitaisService.salvarSinaisVitais(sinaisVitaisParaSalvar);
    }

    @Override
    public TotemCheckin finalizarCheckIn(Long chamadoId) {
        Chamado chamadoIdentificado = chamadoService.buscarChamadoById(chamadoId);

        if (chamadoIdentificado.getStatusChamado() != StatusChamado.AGUARDANDO_TRIAGEM) {
            throw new IllegalStateException("Chamado não está aguardando triagem (status atual:  + " + chamadoIdentificado.getStatusChamado() + " + )");
        }

        if (chamadoIdentificado.getSinaisVitais() == null) {
            throw new IllegalStateException("Sinais vitais ainda não foram capturados para este chamado");
        }

        String senhaGerada = senhaFilaService.gerarSenhaFila(chamadoIdentificado.getPrioridadeChamado());

        Chamado chamadoComAlteracoesSalvas = chamadoService.salvarAlteracoes(chamadoIdentificado.toBuilder()
                .statusChamado(StatusChamado.EM_FILA)
                .senhaFila(senhaGerada)
                .build());

        return new TotemCheckin(
                chamadoComAlteracoesSalvas.getSenhaFila(),
                chamadoComAlteracoesSalvas.getPrioridadeChamado()
        );
    }
}
