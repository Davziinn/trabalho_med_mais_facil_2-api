package com.unifor.MedMaisFacil.dto.totemcheckin;

import com.unifor.MedMaisFacil.enums.PrioridadeChamado;

public record TotemCheckInResponseDTO(
        String senhaFila,
        PrioridadeChamado prioridadeChamado
) {
}
