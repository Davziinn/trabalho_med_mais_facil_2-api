package com.unifor.MedMaisFacil.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TB_TOTEM")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class TotemDispositivoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TOT")
    private Long id;

    @Column(name = "NM_TOT")
    private String nome;

    @ToString.Exclude
    @Column(name = "CV_HSH", nullable = false, unique = true)
    private String chaveHash;

    @Column(name = "IDT_TOT", nullable = false)
    private boolean ativo;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "unidade_saude_id")
    private UnidadeSaudeEntity unidadeSaude;
}
