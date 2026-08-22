package com.unifor.MedMaisFacil.repository;

import com.unifor.MedMaisFacil.entity.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {
}
