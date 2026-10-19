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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
// Isso fui eu? que macaquisse que eu fiz, era pra ser "/v1/chamado"
// Alterar depois (alinhar antes com os fronts enders)
@RequestMapping("/v1/paciente/me/chamado")
@RequiredArgsConstructor
public class ChamadoControllerImpl implements ChamadoController{

    private final ChamadoMapper chamadoMapper;
    private final ChamadoService chamadoService;

    @PostMapping
    public ResponseEntity<ChamadoResponseDTO> criarChamado (@AuthenticationPrincipal Long pacienteId, @Valid @RequestBody ChamadoRequestDTO dto) {
        Chamado chamadoCriado = chamadoService.criarChamado(pacienteId, chamadoMapper.toModel(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(chamadoMapper.toDTO(chamadoCriado));
    }
}
