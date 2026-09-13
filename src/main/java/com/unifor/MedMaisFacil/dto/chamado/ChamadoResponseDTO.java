package com.unifor.MedMaisFacil.dto.chamado;

import com.unifor.MedMaisFacil.dto.orientacoes.OrientacaoResponseDTO;
import com.unifor.MedMaisFacil.dto.unidadeSaude.UnidadeSaudeResponseDTO;
import com.unifor.MedMaisFacil.enums.PrioridadeChamado;
import com.unifor.MedMaisFacil.enums.StatusChamado;

import java.time.LocalDateTime;

public record ChamadoResponseDTO(
        Long id,
        StatusChamado statusChamado,
        PrioridadeChamado prioridadeChamado,
        LocalDateTime dataCriacao,
        OrientacaoResponseDTO orientacoes,
        UnidadeSaudeResponseDTO unidadeSaude
) {
}
