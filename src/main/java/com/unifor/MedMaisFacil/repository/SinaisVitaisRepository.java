package com.unifor.MedMaisFacil.repository;

import com.unifor.MedMaisFacil.entity.SinaisVitaisEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SinaisVitaisRepository extends JpaRepository<SinaisVitaisEntity, Long> {

    Optional<SinaisVitaisEntity> findByChamadoId (Long chamadoId);
}
