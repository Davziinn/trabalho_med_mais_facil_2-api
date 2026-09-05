package com.unifor.MedMaisFacil.service;

import com.unifor.MedMaisFacil.entity.RespostasQuestionario;
import com.unifor.MedMaisFacil.enums.PrioridadeChamado;
import com.unifor.MedMaisFacil.enums.StatusChamado;
import com.unifor.MedMaisFacil.mapper.ChamadoMapper;
import com.unifor.MedMaisFacil.model.Chamado;
import com.unifor.MedMaisFacil.model.Paciente;
import com.unifor.MedMaisFacil.model.QuestionarioSintomas;
import com.unifor.MedMaisFacil.model.classificacao.ProtocoloManchester;
import com.unifor.MedMaisFacil.repository.ChamadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChamadoServiceImpl implements ChamadoService{

    private final PacienteService pacienteService;
    private final ChamadoRepository chamadoRepository;
    private final ChamadoMapper chamadoMapper;

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


        Chamado chamadoCriado = Chamado.builder()
                .id(chamado.getId())
                .statusChamado(StatusChamado.AGUARDANDO_TRIAGEM)
                .senhaFila(gerarSenhaFila())
                .paciente(pacienteEncontrado)
                .questionarioSintomas(questionario)
                .dataCriacao(chamado.getDataCriacao())
                .prioridadeChamado(prioridadeCor)
                .build();

        return chamadoMapper.toModel(chamadoRepository.save(chamadoMapper.toEntity(chamadoCriado)));
    }

    private String gerarSenhaFila() {
        long totalChamados = chamadoRepository.count();
        return "P" + (totalChamados + 1);
    }

}
