package com.unifor.MedMaisFacil.service;

import com.unifor.MedMaisFacil.model.SinaisVitais;
import com.unifor.MedMaisFacil.model.TotemCheckin;
import com.unifor.MedMaisFacil.model.TotemIdentificacao;

public interface TotemService {

    TotemIdentificacao identificarPaciente (String cpf);

    SinaisVitais registrarSinaisVitais (Long chamadoId, SinaisVitais sinaisVitais);

    TotemCheckin finalizarCheckIn (Long chamadoId);
}
