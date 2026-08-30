package com.unifor.MedMaisFacil.exceptions;

public class PacienteNotFoundException extends RuntimeException {
    public PacienteNotFoundException(String message) {
        super(message);
    }
}
