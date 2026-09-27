package com.unifor.MedMaisFacil.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RespostaDetalhe {
    private String pergunta;
    private Boolean resposta;
}
