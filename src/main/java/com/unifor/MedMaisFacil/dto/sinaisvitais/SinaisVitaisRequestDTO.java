package com.unifor.MedMaisFacil.dto.sinaisvitais;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

public record SinaisVitaisRequestDTO(
        @NotNull @Pattern(regexp = "^\\d{2,3}/\\d{2,3}$", message = "Pressão arterial deve estar no formato 120/80") String pressaoArterial,
        @NotNull BigDecimal temperaturaCorporal,
        @NotNull Integer frequenciaCardiaca,
        @NotNull Integer frequenciaRespiratoria,
        @NotNull Integer saturacaoO2
) {
}
