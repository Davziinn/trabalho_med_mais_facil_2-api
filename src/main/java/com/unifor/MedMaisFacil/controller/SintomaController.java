package com.unifor.MedMaisFacil.controller;

import com.unifor.MedMaisFacil.dto.chamado.QuestionarioPerguntasDTO;
import com.unifor.MedMaisFacil.dto.chamado.SintomaOpcaoDTO;
import com.unifor.MedMaisFacil.enums.SintomaPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface SintomaController {

    @GetMapping
    ResponseEntity<List<SintomaOpcaoDTO>> listarSintomas();

    @GetMapping("/{sintoma}/perguntas")
    ResponseEntity<QuestionarioPerguntasDTO> listarPerguntas (@PathVariable SintomaPrincipal sintoma);
}
