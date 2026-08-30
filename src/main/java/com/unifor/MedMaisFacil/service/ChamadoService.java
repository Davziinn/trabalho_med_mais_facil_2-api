package com.unifor.MedMaisFacil.service;

import com.unifor.MedMaisFacil.model.Chamado;

public interface ChamadoService {

    Chamado criarChamado (Long pacienteId, Chamado chamado);
}
