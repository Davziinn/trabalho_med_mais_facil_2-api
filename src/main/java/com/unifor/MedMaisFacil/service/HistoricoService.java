package com.unifor.MedMaisFacil.service;

import com.unifor.MedMaisFacil.model.HistoricoChamadoDetalhe;
import com.unifor.MedMaisFacil.model.HistoricoChamadoResumo;

import java.util.List;

public interface HistoricoService {
    List<HistoricoChamadoResumo> listarHistorico (Long pacienteId);
    HistoricoChamadoDetalhe detalharHistorico (Long pacienteId, Long chamadoId);
}
