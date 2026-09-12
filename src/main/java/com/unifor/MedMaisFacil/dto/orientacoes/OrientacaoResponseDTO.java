package com.unifor.MedMaisFacil.dto.orientacoes;

import java.util.List;

public record OrientacaoResponseDTO(
        List<String> cuidadosCasa,
        List<String> sinaisAlerta
) {
}
