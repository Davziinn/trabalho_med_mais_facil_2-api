package com.unifor.MedMaisFacil.dto.totemcheckin;

public record TotemCheckInRequestDTO(
        // Opcional: sem ele, usa a unidade escolhida pelo paciente no app
        Long unidadeSaudeId
) {
}
