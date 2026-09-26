package com.unifor.MedMaisFacil.service;

import com.unifor.MedMaisFacil.enums.PrioridadeChamado;

public interface SenhaFilaService {
    String gerarSenhaFila(PrioridadeChamado prioridadeChamado);
}
