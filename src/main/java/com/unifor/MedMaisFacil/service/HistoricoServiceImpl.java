package com.unifor.MedMaisFacil.service;

import com.unifor.MedMaisFacil.dto.chamado.PerguntaDTO;
import com.unifor.MedMaisFacil.dto.chamado.QuestionarioPerguntasDTO;
import com.unifor.MedMaisFacil.dto.historicochamadopaciente.RespostaDetalheDTO;
import com.unifor.MedMaisFacil.entity.RespostasQuestionario;
import com.unifor.MedMaisFacil.enums.StatusChamado;
import com.unifor.MedMaisFacil.model.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class HistoricoServiceImpl implements HistoricoService{

    private final ChamadoService chamadoService;

    private final SintomaService sintomaService;

    private static final List<StatusChamado> STATUS_HISTORICO = List.of(
            StatusChamado.FINALIZADO,
            StatusChamado.CANCELADO,
            StatusChamado.EXPIRADO
    );

    @Override
    public List<HistoricoChamadoResumo> listarHistorico(Long pacienteId) {
        return chamadoService.buscarPacienteByIdEByStatusOrdenandoByDataDecrescente(pacienteId, STATUS_HISTORICO)
                .stream()
                .map(chamado -> {
                    return new HistoricoChamadoResumo(
                            chamado.getId(),
                            chamado.getSintomaPrincipal(),
                            chamado.getDataCriacao(),
                            chamado.getPrioridadeChamado(),
                            chamado.getStatusChamado()
                    );
                })
                .toList();
    }

    @Override
    public HistoricoChamadoDetalhe detalharHistorico(Long pacienteId, Long chamadoId) {
        Chamado chamadoEncontrado = chamadoService.buscarChamadoById(chamadoId);

        if(!chamadoEncontrado.getPaciente().getId().equals(pacienteId)) {
            throw new EntityNotFoundException("Chamado não pertence a esse paciente");
        }

        QuestionarioSintomas questionario = chamadoEncontrado.getQuestionarioSintomas();
        QuestionarioPerguntasDTO catalogo = sintomaService.listarPerguntas(questionario.getSintomaPrincipal());
        RespostasQuestionario respostas = questionario.getRespostas();

        return new HistoricoChamadoDetalhe(
                chamadoEncontrado.getId(),
                questionario.getSintomaPrincipal(),
                chamadoEncontrado.getDataCriacao(),
                chamadoEncontrado.getPrioridadeChamado(),
                chamadoEncontrado.getStatusChamado(),
                montarDetalhe(catalogo.discriminadoresGerais(), respostas.discriminadoresGerais()),
                montarDetalhe(catalogo.perguntaFluxograma(), converterParaBoolean(respostas.respostasFluxograma()))
        );
    }

    private List<RespostaDetalheDTO> montarDetalhe(List<PerguntaDTO> perguntas, Map<String, Boolean> respostas) {
        return perguntas.stream()
                .filter(pergunta -> respostas.containsKey(pergunta.chave()))
                .map(pergunta -> new RespostaDetalheDTO(pergunta.texto(), respostas.get(pergunta.chave())))
                .toList();
    }

    private Map<String, Boolean> converterParaBoolean(Map<String, Object> respostasFluxograma) {
        return respostasFluxograma.entrySet().stream()
                .filter(entrada -> entrada.getValue() instanceof Boolean)
                .collect(java.util.stream.Collectors.toMap(Map.Entry::getKey, entrada -> (Boolean) entrada.getValue()));
    }
}
