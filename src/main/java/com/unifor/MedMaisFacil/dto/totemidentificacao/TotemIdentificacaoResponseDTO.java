package com.unifor.MedMaisFacil.dto.totemidentificacao;

import java.util.List;

public record TotemIdentificacaoResponseDTO(
        String nomePaciente,
        List<ChamadoPendenteResponseDTO> chamadosPendentes
) {
}
