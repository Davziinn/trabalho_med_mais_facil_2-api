package com.unifor.MedMaisFacil.service;

import com.unifor.MedMaisFacil.model.TotemIdentificacao;

public interface TotemService {

    TotemIdentificacao identificarPaciente (String cpf);
}
