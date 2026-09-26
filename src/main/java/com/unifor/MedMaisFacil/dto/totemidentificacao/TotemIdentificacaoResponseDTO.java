package com.unifor.MedMaisFacil.dto.totemidentificacao;

import java.util.List;

public record TotemIdentificacaoResponseDTO(
        String nomePaciente,
        int idade,
        List<ChamadoPendenteResponseDTO> chamadosPendentes
) {
}
