package com.unifor.MedMaisFacil.dto.paciente;

import com.unifor.MedMaisFacil.dto.chamado.ChamadoResponseDTO;

import java.time.LocalDate;
import java.util.List;

public record PacienteResponseDTO (
        String nome,
        LocalDate dataNascimento,
        String sexo,
        String email,
        List<ChamadoResponseDTO> chamados
) {}
