package com.unifor.MedMaisFacil.repository;

import com.unifor.MedMaisFacil.entity.TotemDispositivoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TotemDispositivoRepository extends JpaRepository<TotemDispositivoEntity, Long> {

    Optional<TotemDispositivoEntity> findByChaveHashAndAtivoTrue (String chaveHash);
}
