package com.unifor.MedMaisFacil.dto.unidadeSaude;

public record UnidadeSaudeResponseDTO(
        String nome,
        String endereco,
        Double distanciaKm
) {
}
