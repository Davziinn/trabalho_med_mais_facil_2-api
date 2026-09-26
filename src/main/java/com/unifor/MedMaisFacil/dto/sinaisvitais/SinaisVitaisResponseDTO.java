package com.unifor.MedMaisFacil.dto.sinaisvitais;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record SinaisVitaisResponseDTO(
        Long id,
        Long chamadoId,
        String pressaoArterial,
        BigDecimal temperaturaCorporal,
        Integer frequenciaCardiaca,
        Integer frequenciaRespiratoria,
        Integer saturacaoO2,
        LocalDateTime capturadoEm
) {
}
