package com.unifor.MedMaisFacil.mapper;

import com.unifor.MedMaisFacil.entity.SinaisVitaisEntity;
import com.unifor.MedMaisFacil.model.Chamado;
import com.unifor.MedMaisFacil.model.SinaisVitais;
import org.springframework.stereotype.Component;

@Component
public class SinaisVitaisMapperImpl implements SinaisVitaisMapper {

    @Override
    public SinaisVitais toModel(SinaisVitaisEntity entity) {
        Chamado chamadoModel = null;

        if (entity.getChamado() != null) {
            chamadoModel = Chamado.builder()
                    .id(entity.getChamado().getId())
                    .build();
        }

        return SinaisVitais.builder()
                .id(entity.getId())
                .pressaoArterial(entity.getPressaoArterial())
                .temperaturaCorporal(entity.getTemperaturaCorporal())
                .frequenciaCardiaca(entity.getFrequenciaCardiaca())
                .frequenciaRespiratoria(entity.getFrequenciaRespiratoria())
                .saturacaoO2(entity.getSaturacaoO2())
                .capturadoEm(entity.getCapturadoEm())
                .chamado(chamadoModel)
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
                .build();
    }
}
