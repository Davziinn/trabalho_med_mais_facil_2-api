package com.unifor.MedMaisFacil.exceptions;

import com.unifor.MedMaisFacil.enums.PrioridadeChamado;

public class ChamadoJaAbertoException extends RuntimeException {

    public ChamadoJaAbertoException(PrioridadeChamado corExistente) {
        super("Já existe um chamado com prioridade " + corExistente + " em aberto. "
                + "Deseja iniciar um novo atendimento? Isso irá descartar o anterior.");
    }
}
