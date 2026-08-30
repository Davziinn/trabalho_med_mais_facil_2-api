package com.unifor.MedMaisFacil.dto.chamado;

import com.unifor.MedMaisFacil.enums.SintomaPrincipal;
import jakarta.validation.constraints.NotNull;

import java.util.Map;

public record ChamadoRequestDTO(
        @NotNull SintomaPrincipal sintomaPrincipal,
        @NotNull Map<String, Boolean> discriminadoresGerais,
        @NotNull Map<String, Object> respostasFluxograma
) {}
