package com.unifor.MedMaisFacil.entity;

import com.unifor.MedMaisFacil.enums.SintomaPrincipal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "TB_UNIDADE_SAUDE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class UnidadeSaudeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_UNID_SAUD")
    private Long id;

    @Column(name = "NM_UNID", nullable = false)
    private String nome;

    @Column(name = "END_UNID", nullable = false)
    private String endereco;

    @Column(name = "LAT_UNID", nullable = false)
    private Double latitude;

    @Column(name = "LONG_UNID", nullable = false)
    private Double longitude;

    @ElementCollection(targetClass = SintomaPrincipal.class)
    @CollectionTable(name = "TB_UNID_SINTO_ATENDI", joinColumns = @JoinColumn(name = "UNID_ID"))
    @Enumerated(EnumType.STRING)
    @Column(name = "SINT_ATEND")
    private List<SintomaPrincipal> sintomasAtendidos;
}
