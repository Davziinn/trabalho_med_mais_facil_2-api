package com.unifor.MedMaisFacil.controller;

import com.unifor.MedMaisFacil.dto.sinaisvitais.SinaisVitaisRequestDTO;
import com.unifor.MedMaisFacil.dto.sinaisvitais.SinaisVitaisResponseDTO;
import com.unifor.MedMaisFacil.dto.totemcheckin.TotemCheckInResponseDTO;
import com.unifor.MedMaisFacil.dto.totemidentificacao.TotemIdentificacaoResponseDTO;
import com.unifor.MedMaisFacil.mapper.SinaisVitaisMapper;
import com.unifor.MedMaisFacil.mapper.TotemChekInMapper;
import com.unifor.MedMaisFacil.mapper.TotemMapper;
import com.unifor.MedMaisFacil.model.SinaisVitais;
import com.unifor.MedMaisFacil.service.TotemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/totem")
@RequiredArgsConstructor
public class TotemControllerImpl implements TotemController {

    private final TotemService totemService;
    private final TotemMapper totemMapper;

    private final SinaisVitaisMapper sinaisVitaisMapper;

    private final TotemChekInMapper totemChekInMapper;

    @Override
    @GetMapping("/{cpf}/identificacao")
    public ResponseEntity<TotemIdentificacaoResponseDTO> identificarPaciente(@PathVariable String cpf) {
        return ResponseEntity.ok(totemMapper.toDTO(totemService.identificarPaciente(cpf)));
    }

    @Override
    @PostMapping("/{chamadoId}/sinais-vitais")
    public ResponseEntity<SinaisVitaisResponseDTO> registrarSinaisVitais (@PathVariable Long chamadoId, @Valid @RequestBody SinaisVitaisRequestDTO dto) {
        SinaisVitais sinaisVitaisRegistrados = totemService.registrarSinaisVitais(chamadoId, sinaisVitaisMapper.toModel(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(sinaisVitaisMapper.toDTO(sinaisVitaisRegistrados));
    }

    @Override
    @PostMapping("/chamado/{chamadoId}/check-in")
    public ResponseEntity<TotemCheckInResponseDTO> finalizarCheckIn(@PathVariable Long chamadoId) {
        return ResponseEntity.ok(totemChekInMapper.toDTO(totemService.finalizarCheckIn(chamadoId)));
    }
}
