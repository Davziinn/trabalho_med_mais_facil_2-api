package com.unifor.MedMaisFacil.controller;

import com.unifor.MedMaisFacil.dto.totemidentificacao.TotemIdentificacaoResponseDTO;
import com.unifor.MedMaisFacil.mapper.TotemMapper;
import com.unifor.MedMaisFacil.service.TotemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/totem")
@RequiredArgsConstructor
public class TotemControllerImpl implements TotemController {

    private final TotemService totemService;
    private final TotemMapper totemMapper;

    @Override
    @GetMapping("/{cpf}/identificacao")
    public ResponseEntity<TotemIdentificacaoResponseDTO> identificarPaciente(@PathVariable String cpf) {
        return ResponseEntity.ok(totemMapper.toDTO(totemService.identificarPaciente(cpf)));
    }
}
