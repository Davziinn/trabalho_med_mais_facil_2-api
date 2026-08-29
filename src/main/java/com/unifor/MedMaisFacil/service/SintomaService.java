package com.unifor.MedMaisFacil.service;

import com.unifor.MedMaisFacil.dto.chamado.QuestionarioPerguntasDTO;
import com.unifor.MedMaisFacil.dto.chamado.SintomaOpcaoDTO;
import com.unifor.MedMaisFacil.enums.SintomaPrincipal;

import java.util.List;

public interface SintomaService {

    List<SintomaOpcaoDTO> listarSintomas();

    QuestionarioPerguntasDTO listarPerguntas (SintomaPrincipal sintoma);
}
