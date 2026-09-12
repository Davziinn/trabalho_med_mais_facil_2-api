package com.unifor.MedMaisFacil.mapper;

import com.unifor.MedMaisFacil.dto.orientacoes.OrientacaoResponseDTO;
import com.unifor.MedMaisFacil.model.Orientacao;

public interface OrientacaoMapper {

    OrientacaoResponseDTO toDTO (Orientacao model);
}
