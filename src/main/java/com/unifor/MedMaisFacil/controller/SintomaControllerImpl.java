package com.unifor.MedMaisFacil.controller;

import com.unifor.MedMaisFacil.dto.chamado.QuestionarioPerguntasDTO;
import com.unifor.MedMaisFacil.dto.chamado.SintomaOpcaoDTO;
import com.unifor.MedMaisFacil.enums.SintomaPrincipal;
import com.unifor.MedMaisFacil.service.SintomaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/sintoma")
@RequiredArgsConstructor
public class SintomaControllerImpl implements SintomaController{

    private final SintomaService sintomaService;

    @Override
    @GetMapping
    public ResponseEntity<List<SintomaOpcaoDTO>> listarSintomas() {
        List<SintomaOpcaoDTO> sintomaListado = sintomaService.listarSintomas();
        return ResponseEntity.ok(sintomaListado);
    }

    @Override
    @GetMapping("/{sintoma}/perguntas")
    public ResponseEntity<QuestionarioPerguntasDTO> listarPerguntas(SintomaPrincipal sintoma) {
        QuestionarioPerguntasDTO perguntas = sintomaService.listarPerguntas(sintoma);
        return ResponseEntity.ok(perguntas);
    }
}
