package com.unifor.MedMaisFacil.repository;

import com.unifor.MedMaisFacil.entity.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {

    Optional<PacienteEntity> findByCpf (String cpf);
}
