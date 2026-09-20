package com.unifor.MedMaisFacil.controller;

import com.unifor.MedMaisFacil.dto.paciente.PacienteRequestDTO;
import com.unifor.MedMaisFacil.dto.paciente.CadastroPacienteResponseDTO;
import com.unifor.MedMaisFacil.dto.paciente.PacienteResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface PacienteController {

    @PostMapping
    ResponseEntity<CadastroPacienteResponseDTO> cadastrarPaciente (@Valid @RequestBody PacienteRequestDTO dto);

    @GetMapping("/{cpf}")
    ResponseEntity<PacienteResponseDTO> identificacaoPaciente (@PathVariable String cpf);
}
