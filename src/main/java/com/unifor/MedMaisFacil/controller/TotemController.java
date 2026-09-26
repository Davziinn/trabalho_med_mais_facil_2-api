package com.unifor.MedMaisFacil.controller;

import com.unifor.MedMaisFacil.dto.sinaisvitais.SinaisVitaisRequestDTO;
import com.unifor.MedMaisFacil.dto.sinaisvitais.SinaisVitaisResponseDTO;
import com.unifor.MedMaisFacil.dto.totemcheckin.TotemCheckInResponseDTO;
import com.unifor.MedMaisFacil.dto.totemidentificacao.TotemIdentificacaoResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface TotemController {

    @GetMapping("/{cpf}/identificacao")
    ResponseEntity<TotemIdentificacaoResponseDTO> identificarPaciente(@PathVariable String cpf);

    @PostMapping("/{chamadoId}/sinais-vitais")
    ResponseEntity<SinaisVitaisResponseDTO> registrarSinaisVitais (@PathVariable Long chamadoId, @Valid @RequestBody SinaisVitaisRequestDTO dto);

    @PostMapping("/chamado/{chamadoId}/check-in")
    ResponseEntity<TotemCheckInResponseDTO> finalizarCheckIn(@PathVariable Long chamadoId);
}
