package com.unifor.MedMaisFacil.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TB_CONTADOR_SENHA")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ContadorSenhaEntity {


    @EmbeddedId
    @AttributeOverrides({
            @AttributeOverride(name = "dataContagem", column = @Column(name = "DATA_CONTAGEM")),
            @AttributeOverride(name = "prefixo", column = @Column(name = "PREFIXO"))
    })
    private ContadorSenhaPK contadorSenhaPK;

    @Column(name = "CONTADOR", nullable = false)
    private Integer contador;
}
