package com.unifor.MedMaisFacil.dto.chamado;

import java.util.List;

public record QuestionarioPerguntasDTO(
        List<PerguntaDTO> discriminadoresGerais,
        List<PerguntaDTO> perguntaFluxograma
) {
}
