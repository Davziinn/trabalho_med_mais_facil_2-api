package com.unifor.MedMaisFacil.mapper;

import com.unifor.MedMaisFacil.dto.totemcheckin.TotemCheckInResponseDTO;
import com.unifor.MedMaisFacil.model.TotemCheckin;

public interface TotemChekInMapper {

    TotemCheckInResponseDTO toDTO (TotemCheckin model);
}
