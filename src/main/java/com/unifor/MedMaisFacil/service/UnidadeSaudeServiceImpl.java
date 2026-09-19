package com.unifor.MedMaisFacil.service;

import com.unifor.MedMaisFacil.enums.SintomaPrincipal;
import com.unifor.MedMaisFacil.mapper.UnidadeSaudeMapper;
import com.unifor.MedMaisFacil.model.UnidadeSaude;
import com.unifor.MedMaisFacil.repository.UnidadeSaudeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UnidadeSaudeServiceImpl implements UnidadeSaudeService {

    private final UnidadeSaudeRepository unidadeSaudeRepository;
    private final UnidadeSaudeMapper unidadeSaudeMapper;

    private static final double RAIO_TERRA_KM = 6371.0;

    @Override
    public Optional<UnidadeSaude> buscarUnidadeSaudeMaisProxima(SintomaPrincipal sintoma, Double latitudePaciente, Double longitudePaciente) {
        return unidadeSaudeRepository.findAll().stream()
                .map(unidadeSaudeMapper::toModel)
                .filter(unidade -> unidade.getSintomasAtendidos() != null && unidade.getSintomasAtendidos().contains(sintoma))
                .map(unidade -> new UnidadeComDistancia(unidade, calcularDistanciaKm(latitudePaciente, longitudePaciente, unidade.getLatitude(), unidade.getLongitude())))
                .min(Comparator.comparingDouble(UnidadeComDistancia::distanciaKm))
                .map(maisProxima -> new UnidadeSaude(
                        maisProxima.unidadeSaude().getNome(),
                        maisProxima.unidadeSaude.getEndereco(),
                        arredondar(maisProxima.distanciaKm())
                ));
    }

    private record UnidadeComDistancia (UnidadeSaude unidadeSaude, double distanciaKm){}

    private double  arredondar(double valor) {
        return Math.round(valor * 10) / 10.0;
    }

    private double calcularDistanciaKm(Double latitudePaciente, Double longitudePaciente, Double latitude, Double longitude) {
        double dLat = Math.toRadians(latitude - latitudePaciente);
        double dLong = Math.toRadians(longitude - longitudePaciente);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(latitude)) * Math.cos(Math.toRadians(latitudePaciente))
                * Math.sin(dLong / 2) * Math.sin(dLong / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return RAIO_TERRA_KM * c;
    }
}
