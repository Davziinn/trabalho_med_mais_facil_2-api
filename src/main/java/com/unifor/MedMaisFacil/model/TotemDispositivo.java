package com.unifor.MedMaisFacil.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TotemDispositivo {

    private Long id;
    private String nome;
    private String chaveHash;
    private boolean ativo;
    private UnidadeSaude unidadeSaude;
}
