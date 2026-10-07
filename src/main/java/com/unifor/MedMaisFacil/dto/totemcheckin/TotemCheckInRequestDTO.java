package com.unifor.MedMaisFacil.dto.totemcheckin;

import jakarta.validation.constraints.NotNull;

public record TotemCheckInRequestDTO(
        @NotNull Long unidadeSaudeId
) {
}
