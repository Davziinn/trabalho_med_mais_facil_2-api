package com.unifor.MedMaisFacil.dto.sinaisvitais;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record SinaisVitaisRequestDTO(
        @NotNull String pressaoArterial,
        @NotNull BigDecimal temperaturaCorporal,
        @NotNull Integer frequenciaCardiaca,
        @NotNull Integer frequenciaRespiratoria,
        @NotNull Integer saturacaoO2
) {
}
