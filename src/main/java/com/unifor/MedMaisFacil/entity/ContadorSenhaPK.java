package com.unifor.MedMaisFacil.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContadorSenhaPK {

    private LocalDate dataContagem;
    private String prefixo;
}
