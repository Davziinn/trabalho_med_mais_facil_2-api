package com.unifor.MedMaisFacil.repository;

import com.unifor.MedMaisFacil.entity.ChamadoEntity;
import com.unifor.MedMaisFacil.enums.StatusChamado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChamadoRepository extends JpaRepository<ChamadoEntity, Long> {

    List<ChamadoEntity> findByPaciente_IdAndStatusChamado(Long pacienteId, StatusChamado status);
}
