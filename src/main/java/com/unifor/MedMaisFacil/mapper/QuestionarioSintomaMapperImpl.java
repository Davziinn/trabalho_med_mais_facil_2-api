package com.unifor.MedMaisFacil.mapper;

import com.unifor.MedMaisFacil.entity.QuestionarioSintomasEntity;
import com.unifor.MedMaisFacil.model.Chamado;
import com.unifor.MedMaisFacil.model.QuestionarioSintomas;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class QuestionarioSintomaMapperImpl implements QuestionarioSintomaMapper {

    @Override
    public QuestionarioSintomas toModel(QuestionarioSintomasEntity entity) {
        Chamado chamadoModel = null;

        if (entity.getChamado() != null) {
            chamadoModel = Chamado.builder()
                    .id(entity.getChamado().getId())
                    .build();
        }

        return QuestionarioSintomas.builder()
                .id(entity.getId())
                .sintomaPrincipal(entity.getSintomaPrincipal())
                .respostas(entity.getRespostas())
                .chamado(chamadoModel)
                .build();
    }

    @Override
    public QuestionarioSintomasEntity toEntity(QuestionarioSintomas model) {
        return QuestionarioSintomasEntity.builder()
                .id(model.getId())
                .sintomaPrincipal(model.getSintomaPrincipal())
                .respostas(model.getRespostas())
                .build();
    }
}
