package com.unifor.MedMaisFacil.mapper;

import com.unifor.MedMaisFacil.entity.QuestionarioSintomasEntity;
import com.unifor.MedMaisFacil.model.QuestionarioSintomas;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuestionarioSintomaMapperImpl implements QuestionarioSintomaMapper {

    private final ChamadoMapper chamadoMapper;

    @Override
    public QuestionarioSintomas toModel(QuestionarioSintomasEntity entity) {
        return QuestionarioSintomas.builder()
                .id(entity.getId())
                .sintomaPrincipal(entity.getSintomaPrincipal())
                .respostas(entity.getRespostas())
                .chamado(entity.getChamado() != null ? chamadoMapper.toModel(entity.getChamado()) : null)
                .build();
    }

    @Override
    public QuestionarioSintomasEntity toEntity(QuestionarioSintomas model) {
        return QuestionarioSintomasEntity.builder()
                .id(model.getId())
                .sintomaPrincipal(model.getSintomaPrincipal())
                .respostas(model.getRespostas())
                .chamado(model.getChamado() != null ? chamadoMapper.toEntity(model.getChamado()) : null)
                .build();
    }
}
