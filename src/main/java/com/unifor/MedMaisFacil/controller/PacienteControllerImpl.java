package com.unifor.MedMaisFacil.controller;

import com.unifor.MedMaisFacil.dto.paciente.PacienteRequestDTO;
import com.unifor.MedMaisFacil.dto.paciente.CadastroPacienteResponseDTO;
import com.unifor.MedMaisFacil.dto.paciente.PacienteResponseDTO;
import com.unifor.MedMaisFacil.mapper.PacienteMapper;
import com.unifor.MedMaisFacil.model.Paciente;
import com.unifor.MedMaisFacil.service.PacienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/paciente")
@RequiredArgsConstructor
public class PacienteControllerImpl implements PacienteController{

    private final PacienteService pacienteService;
    private final PacienteMapper pacienteMapper;

    @Override
    @PostMapping
    public ResponseEntity<CadastroPacienteResponseDTO> cadastrarPaciente(@Valid @RequestBody PacienteRequestDTO dto) {
        Paciente pacienteCadastrado = pacienteService.cadastrar(pacienteMapper.toModel(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteMapper.toCadastroDTO(pacienteCadastrado));
    }

    @Override
    @GetMapping("/{cpf}")
    public ResponseEntity<PacienteResponseDTO> identificacaoPaciente (@PathVariable String cpf) {
        Paciente pacienteIdentificado = pacienteService.buscarPacienteByCpf(cpf);
        return ResponseEntity.ok(pacienteMapper.toDTO(pacienteIdentificado));
    }
}
