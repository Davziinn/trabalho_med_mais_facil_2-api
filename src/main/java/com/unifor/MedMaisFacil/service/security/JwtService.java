package com.unifor.MedMaisFacil.service.security;

public interface JwtService {

    String gerarToken (Long pacienteId, String nome);

    Long extrairPacienteId (String token);

    boolean isValidToken (String token);
}
