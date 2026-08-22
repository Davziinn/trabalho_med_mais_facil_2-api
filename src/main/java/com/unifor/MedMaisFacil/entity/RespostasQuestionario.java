package com.unifor.MedMaisFacil.entity;

import java.util.Map;

public record RespostasQuestionario (
        Map<String, Boolean> discriminadoresGerais,
        Map<String, Object> respostasFluxograma
) {
}
