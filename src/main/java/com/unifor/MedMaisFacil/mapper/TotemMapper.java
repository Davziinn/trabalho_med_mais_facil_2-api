package com.unifor.MedMaisFacil.mapper;

import com.unifor.MedMaisFacil.dto.totemidentificacao.TotemIdentificacaoResponseDTO;
import com.unifor.MedMaisFacil.model.TotemIdentificacao;

public interface TotemMapper {

    TotemIdentificacaoResponseDTO toDTO(TotemIdentificacao model);
}
