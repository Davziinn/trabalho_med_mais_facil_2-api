package com.unifor.MedMaisFacil.mapper;

import com.unifor.MedMaisFacil.dto.unidadeSaude.UnidadeSaudeResponseDTO;
import com.unifor.MedMaisFacil.entity.UnidadeSaudeEntity;
import com.unifor.MedMaisFacil.model.UnidadeSaude;
import org.springframework.stereotype.Component;

@Component
public class UnidadeSaudeMapperImpl implements UnidadeSaudeMapper {

    @Override
    public UnidadeSaude toModel(UnidadeSaudeEntity entity) {
        return UnidadeSaude.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .endereco(entity.getEndereco())
                .latitude(entity.getLatitude())
                .longitude(entity.getLongitude())
                .sintomasAtendidos(entity.getSintomasAtendidos())
                .build();
    }

    @Override
    public UnidadeSaudeEntity toEntity(UnidadeSaude model) {
        return UnidadeSaudeEntity.builder()
                .id(model.getId())
                .nome(model.getNome())
                .endereco(model.getEndereco())
                .latitude(model.getLatitude())
                .longitude(model.getLongitude())
                .sintomasAtendidos(model.getSintomasAtendidos())
                .build();
    }

    @Override
    public UnidadeSaudeResponseDTO toDTO(UnidadeSaude model) {
        return new UnidadeSaudeResponseDTO(
                model.getNome(),
                model.getEndereco(),
                model.getDistanciaKm()
        );
    }
}