package com.unifor.MedMaisFacil.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class SinaisVitais {

    private Long id;
    private String pressaoArterial;
    private BigDecimal temperaturaCorporal;
    private Integer frequenciaCardiaca;
    private Integer frequenciaRespiratoria;
    private Integer saturacaoO2;
    private LocalDateTime capturadoEm;
    private Chamado chamado;

}
