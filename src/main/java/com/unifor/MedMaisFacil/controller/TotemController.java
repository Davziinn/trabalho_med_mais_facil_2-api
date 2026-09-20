package com.unifor.MedMaisFacil.controller;

import com.unifor.MedMaisFacil.dto.totemidentificacao.TotemIdentificacaoResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface TotemController {

    @GetMapping("/{cpf}/identificacao")
    ResponseEntity<TotemIdentificacaoResponseDTO> identificarPaciente(@PathVariable String cpf);
}
