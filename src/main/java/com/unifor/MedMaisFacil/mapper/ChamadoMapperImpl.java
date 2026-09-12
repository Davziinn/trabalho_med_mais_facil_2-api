package com.unifor.MedMaisFacil.mapper;

import com.unifor.MedMaisFacil.dto.chamado.ChamadoRequestDTO;
import com.unifor.MedMaisFacil.dto.chamado.ChamadoResponseDTO;
import com.unifor.MedMaisFacil.entity.ChamadoEntity;
import com.unifor.MedMaisFacil.entity.PacienteEntity;
import com.unifor.MedMaisFacil.entity.SinaisVitaisEntity;
import com.unifor.MedMaisFacil.entity.QuestionarioSintomasEntity;
import com.unifor.MedMaisFacil.model.Chamado;
import com.unifor.MedMaisFacil.model.Paciente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChamadoMapperImpl implements ChamadoMapper {

    private final SinaisVitaisMapper sinaisVitaisMapper;
    private final QuestionarioSintomaMapper questionarioSintomaMapper;
    private final OrientacaoMapper orientacaoMapper;

    @Override
    public Chamado toModel(ChamadoEntity entity) {
        return Chamado.builder()
                .id(entity.getId())
                .prioridadeChamado(entity.getPrioridadeChamado())
                .statusChamado(entity.getStatusChamado())
                .senhaFila(entity.getSenhaFila())
                .dataCriacao(entity.getDataCriacao())
                .paciente(entity.getPaciente() != null
                        ? Paciente.builder().id(entity.getPaciente().getId()).build()
                        : null)
                .sinaisVitais(entity.getSinaisVitais() != null ? sinaisVitaisMapper.toModel(entity.getSinaisVitais()) : null)
                .questionarioSintomas(entity.getQuestionarioSintomas() != null ? questionarioSintomaMapper.toModel(entity.getQuestionarioSintomas()) : null)
                .build();
    }

    @Override
    public ChamadoEntity toEntity(Chamado model) {
        ChamadoEntity entity = ChamadoEntity.builder()
                .id(model.getId())
                .prioridadeChamado(model.getPrioridadeChamado())
                .statusChamado(model.getStatusChamado())
                .senhaFila(model.getSenhaFila())
                .dataCriacao(model.getDataCriacao())
                .paciente(model.getPaciente() != null
                        ? PacienteEntity.builder().id(model.getPaciente().getId()).build()
                        : null)
                .build();

        if (model.getSinaisVitais() != null) {
            SinaisVitaisEntity sinaisVitaisEntity = sinaisVitaisMapper.toEntity(model.getSinaisVitais());
            sinaisVitaisEntity.setChamado(entity);
            entity.setSinaisVitais(sinaisVitaisEntity);
        }

        if (model.getQuestionarioSintomas() != null) {
            QuestionarioSintomasEntity questionarioEntity = questionarioSintomaMapper.toEntity(model.getQuestionarioSintomas());
            questionarioEntity.setChamado(entity);
            entity.setQuestionarioSintomas(questionarioEntity);
        }

        return entity;
    }

    @Override
    public Chamado toModel(ChamadoRequestDTO dto) {
        return Chamado.builder()
                .sintomaPrincipal(dto.sintomaPrincipal())
                .discriminadoresGerais(dto.discriminadoresGerais())
                .respostasFluxograma(dto.respostasFluxograma())
                .build();
    }

    @Override
    public ChamadoResponseDTO toDTO(Chamado model) {
        return new ChamadoResponseDTO(
                model.getId(),
                model.getStatusChamado(),
                model.getPrioridadeChamado(),
                model.getDataCriacao(),
                model.getOrientacao() != null ? orientacaoMapper.toDTO(model.getOrientacao()) : null
        );
    }
}