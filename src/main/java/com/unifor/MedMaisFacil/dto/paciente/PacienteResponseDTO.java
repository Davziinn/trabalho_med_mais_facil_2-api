package com.unifor.MedMaisFacil.dto.paciente;

import com.unifor.MedMaisFacil.dto.chamado.ChamadoResponseDTO;

import java.time.LocalDate;
import java.util.List;

public record PacienteResponseDTO (
        Long id,
        String nome,
        LocalDate dataNascimento,
        String sexo,
        String email,
        List<ChamadoResponseDTO> chamados
) {}
