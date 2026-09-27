package com.unifor.MedMaisFacil.repository;

import com.unifor.MedMaisFacil.entity.ChamadoEntity;
import com.unifor.MedMaisFacil.enums.PrioridadeChamado;
import com.unifor.MedMaisFacil.enums.StatusChamado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ChamadoRepository extends JpaRepository<ChamadoEntity, Long> {

    List<ChamadoEntity> findByPaciente_IdAndStatusChamado(Long pacienteId, StatusChamado status);

    List<ChamadoEntity> findByPaciente_IdAndStatusChamadoAndPrioridadeChamadoInAndDataCriacaoBefore(Long pacienteId, StatusChamado status, List<PrioridadeChamado> cores, LocalDateTime antesDe);

    List<ChamadoEntity> findByPaciente_IdAndStatusChamadoInOrderByDataCriacaoDesc(Long pacienteId, List<StatusChamado> status);
}
