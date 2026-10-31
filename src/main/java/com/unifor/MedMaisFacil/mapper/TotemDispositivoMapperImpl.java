package com.unifor.MedMaisFacil.mapper;

import com.unifor.MedMaisFacil.entity.TotemDispositivoEntity;
import com.unifor.MedMaisFacil.model.TotemDispositivo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TotemDispositivoMapperImpl implements TotemDispositivoMapper {

    private final UnidadeSaudeMapper unidadeSaudeMapper;

    @Override
    public TotemDispositivoEntity toEntity(TotemDispositivo model) {
        return new TotemDispositivoEntity(
                model.getId(),
                model.getNome(),
                model.getChaveHash(),
                model.isAtivo(),
                model.getUnidadeSaude() != null ? unidadeSaudeMapper.toEntity(model.getUnidadeSaude()) : null
        );
    }

    @Override
    public TotemDispositivo toModel(TotemDispositivoEntity entity) {
        return new TotemDispositivo(
                entity.getId(),
                entity.getNome(),
                entity.getChaveHash(),
                entity.isAtivo(),
                entity.getUnidadeSaude() != null ? unidadeSaudeMapper.toModel(entity.getUnidadeSaude()) : null
        );
    }
}
