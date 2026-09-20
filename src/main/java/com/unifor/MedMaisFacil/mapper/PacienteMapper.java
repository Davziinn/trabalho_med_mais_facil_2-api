package com.unifor.MedMaisFacil.mapper;

import com.unifor.MedMaisFacil.dto.paciente.PacienteRequestDTO;
import com.unifor.MedMaisFacil.dto.paciente.CadastroPacienteResponseDTO;
import com.unifor.MedMaisFacil.dto.paciente.PacienteResponseDTO;
import com.unifor.MedMaisFacil.entity.PacienteEntity;
import com.unifor.MedMaisFacil.model.Paciente;

public interface PacienteMapper {

    Paciente toModel (PacienteEntity entity);

    PacienteEntity toEntity (Paciente model);

    Paciente toModel (PacienteRequestDTO dto);

    CadastroPacienteResponseDTO toCadastroDTO (Paciente model);

    PacienteResponseDTO toDTO(Paciente model);
}
