package com.unifor.MedMaisFacil.service;

import com.unifor.MedMaisFacil.model.TotemDispositivo;

import java.util.Optional;

public interface TotemDispositivoService {

    Optional<TotemDispositivo> buscarPorChave(String chaveBruta);
}
