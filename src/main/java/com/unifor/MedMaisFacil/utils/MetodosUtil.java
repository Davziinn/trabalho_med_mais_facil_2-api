package com.unifor.MedMaisFacil.utils;

import java.time.LocalDate;
import java.time.Period;

public class MetodosUtil {

    public static int calcularIdade (LocalDate dataNascimento) {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }
}
