package com.unifor.MedMaisFacil.controller;

import com.unifor.MedMaisFacil.dto.chamado.ChamadoRequestDTO;
import com.unifor.MedMaisFacil.dto.chamado.ChamadoResponseDTO;
import com.unifor.MedMaisFacil.mapper.ChamadoMapper;
import com.unifor.MedMaisFacil.model.Chamado;
import com.unifor.MedMaisFacil.service.ChamadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/paciente/{pacienteId}/chamado")
@RequiredArgsConstructor
public class ChamadoControllerImpl implements ChamadoController{

    private final ChamadoMapper chamadoMapper;
    private final ChamadoService chamadoService;

    @PostMapping
    public ResponseEntity<ChamadoResponseDTO> criarChamado (@PathVariable Long pacienteId, @Valid @RequestBody ChamadoRequestDTO dto) {
        Chamado chamadoCriado = chamadoService.criarChamado(pacienteId, chamadoMapper.toModel(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(chamadoMapper.toDTO(chamadoCriado));
    }
}
