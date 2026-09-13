package com.unifor.MedMaisFacil.service;

import com.unifor.MedMaisFacil.enums.PrioridadeChamado;
import com.unifor.MedMaisFacil.enums.SintomaPrincipal;
import com.unifor.MedMaisFacil.model.Orientacao;

public interface OrientacaoService {

    Orientacao buscarOrientacao (SintomaPrincipal sintomaPrincipal, PrioridadeChamado prioridade);
}
