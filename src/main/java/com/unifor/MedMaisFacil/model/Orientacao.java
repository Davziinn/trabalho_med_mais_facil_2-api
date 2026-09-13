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
public class Orientacao {

    private boolean urgente;
    private String mensagemUrgente;
    private List<String> cuidadosCasa;
    private List<String> sinaisAlerta;
}
