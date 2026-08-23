package com.unifor.MedMaisFacil.mapper;

import com.unifor.MedMaisFacil.entity.PacienteEntity;
import com.unifor.MedMaisFacil.model.Paciente;

public interface PacienteMapper {

    Paciente toModel (PacienteEntity entity);

    PacienteEntity toEntity (Paciente model);
}
