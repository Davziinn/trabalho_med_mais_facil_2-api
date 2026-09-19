package com.unifor.MedMaisFacil.service;

import com.unifor.MedMaisFacil.enums.SintomaPrincipal;
import com.unifor.MedMaisFacil.model.UnidadeSaude;

import java.util.Optional;

public interface UnidadeSaudeService {
    Optional<UnidadeSaude> buscarUnidadeSaudeMaisProxima (SintomaPrincipal sintoma, Double latitudePaciente, Double longitudePaciente);
}
