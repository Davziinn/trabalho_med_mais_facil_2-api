package com.unifor.MedMaisFacil.mapper;

import com.unifor.MedMaisFacil.dto.sinaisvitais.SinaisVitaisRequestDTO;
import com.unifor.MedMaisFacil.dto.sinaisvitais.SinaisVitaisResponseDTO;
import com.unifor.MedMaisFacil.entity.SinaisVitaisEntity;
import com.unifor.MedMaisFacil.model.SinaisVitais;

public interface SinaisVitaisMapper {

    SinaisVitais toModel (SinaisVitaisEntity entity);

    SinaisVitaisEntity toEntity (SinaisVitais model);

    SinaisVitaisResponseDTO toDTO (SinaisVitais model);

    SinaisVitais toModel (SinaisVitaisRequestDTO dto);
}
