package com.unifor.MedMaisFacil.dto.chamado;

import com.unifor.MedMaisFacil.enums.PrioridadeChamado;
import com.unifor.MedMaisFacil.enums.StatusChamado;

import java.time.LocalDateTime;

public record ChamadoResponseDTO(
        Long id,
        String senhaFila,
        StatusChamado statusChamado,
        PrioridadeChamado prioridadeChamado,
        LocalDateTime dataCriacao
) {
}
