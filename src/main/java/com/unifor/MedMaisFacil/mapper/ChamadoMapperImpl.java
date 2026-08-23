package com.unifor.MedMaisFacil.mapper;

import com.unifor.MedMaisFacil.entity.ChamadoEntity;
import com.unifor.MedMaisFacil.model.Chamado;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChamadoMapperImpl implements ChamadoMapper {

    private final PacienteMapper pacienteMapper;
    private final SinaisVitaisMapper sinaisVitaisMapper;
    private final QuestionarioSintomaMapper questionarioSintomaMapper;

    @Override
    public Chamado toModel(ChamadoEntity entity) {
        return Chamado.builder()
                .id(entity.getId())
                .prioridadeChamado(entity.getPrioridadeChamado())
                .statusChamado(entity.getStatusChamado())
                .senhaFila(entity.getSenhaFila())
                .dataCriacao(entity.getDataCriacao())
                .paciente(entity.getPaciente() != null ? pacienteMapper.toModel(entity.getPaciente()) : null)
                .sinaisVitais(entity.getSinaisVitais() != null ? sinaisVitaisMapper.toModel(entity.getSinaisVitais()) : null)
                .questionarioSintomas(entity.getQuestionarioSintomas() != null ? questionarioSintomaMapper.toModel(entity.getQuestionarioSintomas()) : null)
                .build();
    }

    @Override
    public ChamadoEntity toEntity(Chamado model) {
        return ChamadoEntity.builder()
                .id(model.getId())
                .prioridadeChamado(model.getPrioridadeChamado())
                .statusChamado(model.getStatusChamado())
                .senhaFila(model.getSenhaFila())
                .dataCriacao(model.getDataCriacao())
                .paciente(model.getPaciente() != null ? pacienteMapper.toEntity(model.getPaciente()) : null)
                .sinaisVitais(model.getSinaisVitais() != null ? sinaisVitaisMapper.toEntity(model.getSinaisVitais()) : null)
                .questionarioSintomas(model.getQuestionarioSintomas() != null ? questionarioSintomaMapper.toEntity(model.getQuestionarioSintomas()) : null)
                .build();
    }
}
