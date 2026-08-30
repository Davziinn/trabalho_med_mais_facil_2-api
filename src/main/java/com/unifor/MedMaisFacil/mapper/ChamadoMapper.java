package com.unifor.MedMaisFacil.mapper;

import com.unifor.MedMaisFacil.dto.chamado.ChamadoRequestDTO;
import com.unifor.MedMaisFacil.dto.chamado.ChamadoResponseDTO;
import com.unifor.MedMaisFacil.entity.ChamadoEntity;
import com.unifor.MedMaisFacil.model.Chamado;

public interface ChamadoMapper {

    Chamado toModel (ChamadoEntity entity);

    ChamadoEntity toEntity (Chamado model);

    Chamado toModel (ChamadoRequestDTO dto);

    ChamadoResponseDTO toDTO (Chamado model);
}
