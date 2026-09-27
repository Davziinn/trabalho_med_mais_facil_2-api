package com.unifor.MedMaisFacil.mapper;

import com.unifor.MedMaisFacil.dto.historicochamadopaciente.HistoricoChamadoDetalheDTO;
import com.unifor.MedMaisFacil.dto.historicochamadopaciente.HistoricoChamadoResumoDTO;
import com.unifor.MedMaisFacil.dto.historicochamadopaciente.RespostaDetalheDTO;
import com.unifor.MedMaisFacil.model.HistoricoChamadoDetalhe;
import com.unifor.MedMaisFacil.model.HistoricoChamadoResumo;
import com.unifor.MedMaisFacil.model.RespostaDetalhe;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HistoricoMapperImpl implements HistoricoMapper {

    @Override
    public HistoricoChamadoDetalheDTO toDetalheDTO(HistoricoChamadoDetalhe model) {
        return new HistoricoChamadoDetalheDTO(
                model.getChamadoId(),
                model.getSintomaPrincipal(),
                model.getDataCriacao(),
                model.getPrioridadeChamado(),
                model.getStatusChamado(),
                model.getDiscriminadoresGerais(),
                model.getRespostasFluxograma()
        );
    }

    @Override
    public HistoricoChamadoResumoDTO toResumoDTO(HistoricoChamadoResumo model) {
        return new HistoricoChamadoResumoDTO(
                model.getChamadoId(),
                model.getSintomaPrincipal(),
                model.getDataCriacao(),
                model.getPrioridade(),
                model.getStatus()
        );
    }

    @Override
    public List<RespostaDetalheDTO> toRespostaDTO(List<RespostaDetalhe> model) {
        return model.stream()
                .map(resposta -> new RespostaDetalheDTO(
                        resposta.getPergunta(),
                        resposta.getResposta()
                ))
                .toList();
    }
}
