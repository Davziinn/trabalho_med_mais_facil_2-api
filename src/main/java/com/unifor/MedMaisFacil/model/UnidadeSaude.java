package com.unifor.MedMaisFacil.model;

import com.unifor.MedMaisFacil.enums.SintomaPrincipal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class UnidadeSaude {

    private Long id;
    private String nome;
    private String endereco;
    private Double latitude;
    private Double longitude;
    private List<SintomaPrincipal> sintomasAtendidos;
}
