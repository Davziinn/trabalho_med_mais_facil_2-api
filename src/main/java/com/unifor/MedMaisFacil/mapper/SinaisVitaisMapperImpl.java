package com.unifor.MedMaisFacil.mapper;

import com.unifor.MedMaisFacil.entity.SinaisVitaisEntity;
import com.unifor.MedMaisFacil.model.SinaisVitais;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SinaisVitaisMapperImpl implements SinaisVitaisMapper {

    private final ChamadoMapper chamadoMapper;

    @Override
    public SinaisVitais toModel(SinaisVitaisEntity entity) {
        return SinaisVitais.builder()
                .id(entity.getId())
                .pressaoArterial(entity.getPressaoArterial())
                .temperaturaCorporal(entity.getTemperaturaCorporal())
                .frequenciaCardiaca(entity.getFrequenciaCardiaca())
                .frequenciaRespiratoria(entity.getFrequenciaRespiratoria())
                .saturacaoO2(entity.getSaturacaoO2())
                .capturadoEm(entity.getCapturadoEm())
                .chamado(entity.getChamado() != null ? chamadoMapper.toModel(entity.getChamado()) : null)
                .build();
    }

    @Override
    public SinaisVitaisEntity toEntity(SinaisVitais model) {
        return SinaisVitaisEntity.builder()
                .id(model.getId())
                .pressaoArterial(model.getPressaoArterial())
                .temperaturaCorporal(model.getTemperaturaCorporal())
                .frequenciaCardiaca(model.getFrequenciaCardiaca())
                .frequenciaRespiratoria(model.getFrequenciaRespiratoria())
                .saturacaoO2(model.getSaturacaoO2())
                .capturadoEm(model.getCapturadoEm())
                .chamado(model.getChamado() != null ? chamadoMapper.toEntity(model.getChamado()) : null)
                .build();
    }
}
