package com.unifor.MedMaisFacil.mapper;

import com.unifor.MedMaisFacil.dto.orientacoes.OrientacaoResponseDTO;
import com.unifor.MedMaisFacil.model.Orientacao;
import org.springframework.stereotype.Component;

@Component
public class OrientacaoMapperImpl implements OrientacaoMapper {

    @Override
    public OrientacaoResponseDTO toDTO(Orientacao model) {
        return new OrientacaoResponseDTO(
                model.getCuidadosCasa(),
                model.getSinaisAlerta()
        );
    }
}
