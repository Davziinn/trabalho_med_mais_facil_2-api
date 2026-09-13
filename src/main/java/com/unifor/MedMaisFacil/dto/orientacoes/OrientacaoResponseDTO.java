package com.unifor.MedMaisFacil.dto.orientacoes;

import java.util.List;

public record OrientacaoResponseDTO(
        boolean urgente,
        String mensagemUrgencia,
        List<String> cuidadosCasa,
        List<String> sinaisAlerta
) {
}
