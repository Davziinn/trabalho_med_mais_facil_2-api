package com.unifor.MedMaisFacil.mapper;

import com.unifor.MedMaisFacil.entity.QuestionarioSintomasEntity;
import com.unifor.MedMaisFacil.model.QuestionarioSintomas;

public interface QuestionarioSintomaMapper {

    QuestionarioSintomas toModel (QuestionarioSintomasEntity entity);

    QuestionarioSintomasEntity toEntity (QuestionarioSintomas model);
}
