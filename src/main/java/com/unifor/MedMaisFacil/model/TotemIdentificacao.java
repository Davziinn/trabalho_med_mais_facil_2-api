package com.unifor.MedMaisFacil.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TotemIdentificacao {

    private Long pacienteId;
    private String nomePaciente;
    private int idade;
    private List<ChamadoPendente> chamadosPendentes;
}
