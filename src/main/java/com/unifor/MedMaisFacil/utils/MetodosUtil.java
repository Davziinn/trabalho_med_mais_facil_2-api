package com.unifor.MedMaisFacil.utils;

import java.time.LocalDate;
import java.time.Period;

public class MetodosUtil {

    public static int calcularIdade (LocalDate dataNascimento) {
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

    public static int extrairSistolica (String pressaoArterial) {
        return Integer.parseInt(pressaoArterial.split("/")[0].trim());
    }

    public static int extrairDiastolica (String pressaoArterial) {
        return Integer.parseInt(pressaoArterial.split("/")[1].trim());
    }
}
