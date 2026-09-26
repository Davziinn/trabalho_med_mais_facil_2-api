package com.unifor.MedMaisFacil.service;

import com.unifor.MedMaisFacil.mapper.SinaisVitaisMapper;
import com.unifor.MedMaisFacil.model.SinaisVitais;
import com.unifor.MedMaisFacil.repository.SinaisVitaisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SinaisVitaisServiceImpl implements SinaisVitaisService {

    private final SinaisVitaisRepository sinaisVitaisRepository;
    private final SinaisVitaisMapper sinaisVitaisMapper;

    @Override
    public SinaisVitais salvarSinaisVitais(SinaisVitais sinaisVitais) {
        return sinaisVitaisMapper.toModel(sinaisVitaisRepository.save(sinaisVitaisMapper.toEntity(sinaisVitais)));
    }
}
