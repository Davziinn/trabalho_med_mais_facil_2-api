package com.unifor.MedMaisFacil.dto.unidadeSaude;

public record UnidadeSaudeResponseDTO(
        Long id,
        String nome,
        String endereco,
        Double distanciaKm,
        Boolean lotada
) {
}
