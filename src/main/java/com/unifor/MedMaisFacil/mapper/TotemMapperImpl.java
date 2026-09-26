package com.unifor.MedMaisFacil.mapper;

import com.unifor.MedMaisFacil.dto.totemidentificacao.ChamadoPendenteResponseDTO;
import com.unifor.MedMaisFacil.dto.totemidentificacao.TotemIdentificacaoResponseDTO;
import com.unifor.MedMaisFacil.model.TotemIdentificacao;
import org.springframework.stereotype.Component;

@Component
public class TotemMapperImpl implements TotemMapper {

    @Override
    public TotemIdentificacaoResponseDTO toDTO(TotemIdentificacao model) {
        return new TotemIdentificacaoResponseDTO(
                model.getNomePaciente(),
                model.getIdade(),
                model.getChamadosPendentes() != null ? model.getChamadosPendentes().stream().map(
                        chamadoPendente -> new ChamadoPendenteResponseDTO(
                                chamadoPendente.getChamadoId(),
                                chamadoPendente.getSintomaPrincipal(),
                                chamadoPendente.getDataCriacao()
                        )
                ).toList() : null
        );
    }
}
