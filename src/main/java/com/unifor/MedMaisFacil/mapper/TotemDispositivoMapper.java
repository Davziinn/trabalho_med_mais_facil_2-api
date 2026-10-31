package com.unifor.MedMaisFacil.mapper;

import com.unifor.MedMaisFacil.entity.TotemDispositivoEntity;
import com.unifor.MedMaisFacil.model.TotemDispositivo;

public interface TotemDispositivoMapper {

    TotemDispositivoEntity toEntity (TotemDispositivo model);

    TotemDispositivo toModel (TotemDispositivoEntity entity);
}
