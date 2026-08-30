package com.unifor.MedMaisFacil.controller;

import com.unifor.MedMaisFacil.dto.chamado.ChamadoRequestDTO;
import com.unifor.MedMaisFacil.dto.chamado.ChamadoResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ChamadoController {

    @PostMapping
    ResponseEntity<ChamadoResponseDTO> criarChamado (@PathVariable Long pacienteId, @Valid @RequestBody ChamadoRequestDTO dto);
}
