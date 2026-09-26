package com.unifor.MedMaisFacil.model;

import com.unifor.MedMaisFacil.enums.PrioridadeChamado;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TotemCheckin {

    private String senhaFila;
    private PrioridadeChamado prioridadeChamado;
}
