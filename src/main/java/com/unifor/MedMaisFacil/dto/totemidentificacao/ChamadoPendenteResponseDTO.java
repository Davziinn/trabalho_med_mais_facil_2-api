package com.unifor.MedMaisFacil.dto.totemidentificacao;

import com.unifor.MedMaisFacil.enums.SintomaPrincipal;

import java.time.LocalDateTime;

public record ChamadoPendenteResponseDTO (
        Long chamadoId,
        SintomaPrincipal sintomaPrincipal,
        LocalDateTime dataCriacao
) {
}
