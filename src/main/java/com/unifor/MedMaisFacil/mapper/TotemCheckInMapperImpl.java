package com.unifor.MedMaisFacil.mapper;

import com.unifor.MedMaisFacil.dto.totemcheckin.TotemCheckInResponseDTO;
import com.unifor.MedMaisFacil.model.TotemCheckin;
import org.springframework.stereotype.Component;

@Component
public class TotemCheckInMapperImpl implements TotemChekInMapper {
    @Override
    public TotemCheckInResponseDTO toDTO(TotemCheckin model) {
        return new TotemCheckInResponseDTO(model.getSenhaFila(), model.getPrioridadeChamado());
    }
}
