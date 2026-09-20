package com.unifor.MedMaisFacil.service;

import com.unifor.MedMaisFacil.model.Chamado;
import com.unifor.MedMaisFacil.model.ChamadoPendente;
import com.unifor.MedMaisFacil.model.Paciente;
import com.unifor.MedMaisFacil.model.TotemIdentificacao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TotemServiceImpl implements TotemService {

    private final PacienteService pacienteService;

    private final ChamadoService chamadoService;

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

        return new TotemIdentificacao(pacienteEncontrado.getNome(), chamadosPendentes);
    }
}
