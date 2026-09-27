package com.unifor.MedMaisFacil.mapper;

import com.unifor.MedMaisFacil.dto.historicochamadopaciente.HistoricoChamadoDetalheDTO;
import com.unifor.MedMaisFacil.dto.historicochamadopaciente.HistoricoChamadoResumoDTO;
import com.unifor.MedMaisFacil.dto.historicochamadopaciente.RespostaDetalheDTO;
import com.unifor.MedMaisFacil.model.HistoricoChamadoDetalhe;
import com.unifor.MedMaisFacil.model.HistoricoChamadoResumo;
import com.unifor.MedMaisFacil.model.RespostaDetalhe;

import java.util.List;

public interface HistoricoMapper {
    HistoricoChamadoDetalheDTO toDetalheDTO (HistoricoChamadoDetalhe model);
    HistoricoChamadoResumoDTO toResumoDTO (HistoricoChamadoResumo model);
    List<RespostaDetalheDTO> toRespostaDTO(List<RespostaDetalhe> model);
}
