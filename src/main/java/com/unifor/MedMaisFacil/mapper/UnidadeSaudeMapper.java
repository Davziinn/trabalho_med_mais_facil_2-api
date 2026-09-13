package com.unifor.MedMaisFacil.mapper;

import com.unifor.MedMaisFacil.dto.unidadeSaude.UnidadeSaudeResponseDTO;
import com.unifor.MedMaisFacil.entity.UnidadeSaudeEntity;
import com.unifor.MedMaisFacil.model.UnidadeSaude;

public interface UnidadeSaudeMapper {

    UnidadeSaude toModel (UnidadeSaudeEntity entity);

    UnidadeSaudeEntity toEntity (UnidadeSaude model);

    UnidadeSaudeResponseDTO toDTO (UnidadeSaude model);
}
