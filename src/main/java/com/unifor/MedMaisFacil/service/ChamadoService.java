package com.unifor.MedMaisFacil.service;

import com.unifor.MedMaisFacil.model.Chamado;

import java.util.List;

public interface ChamadoService {

    Chamado criarChamado (Long pacienteId, Chamado chamado);

    List<Chamado> buscarChamadoByPacienteIdAndStatus (Long pacienteId);
}
