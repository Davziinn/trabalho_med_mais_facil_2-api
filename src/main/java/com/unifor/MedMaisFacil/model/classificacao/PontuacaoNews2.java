package com.unifor.MedMaisFacil.model.classificacao;

import com.unifor.MedMaisFacil.model.SinaisVitais;
import com.unifor.MedMaisFacil.utils.MetodosUtil;

import java.math.BigDecimal;

public class PontuacaoNews2 {
    public record Resultado (int pontuacaoTotal, int maiorPontuacaoIndividual) {}

    public static Resultado calcular (SinaisVitais sinaisVitais) {
        int [] pontos = {
                pontosFrequenciaCardiaca(sinaisVitais.getFrequenciaCardiaca()),
                pontosSaturacaoO2(sinaisVitais.getSaturacaoO2()),
                pontosTemperaturaCorporal(sinaisVitais.getTemperaturaCorporal()),
                pontosPressaoSistolica(MetodosUtil.extrairSistolica(sinaisVitais.getPressaoArterial())),
                pontosFrequenciaRespiratoria(sinaisVitais.getFrequenciaRespiratoria())
        };

        int total = 0;
        int maior = 0;
        for (int ponto : pontos) {
            total += ponto;
            maior = Math.max(maior, ponto);
        }

        return new Resultado(total, maior);
    }

    static int pontosFrequenciaCardiaca (int freqCard) {
        if (freqCard <= 40) return 3;
        if (freqCard <= 50) return 1;
        if (freqCard <= 90) return 0;
        if (freqCard <= 110) return 1;
        if (freqCard <= 130) return 2;

        return 3;
    }

    static int pontosSaturacaoO2 (int saturacao) {
        if (saturacao <= 91) return 3;
        if (saturacao <= 93) return 2;
        if (saturacao <= 95) return 1;

        return 0;
    }

    static int pontosTemperaturaCorporal (BigDecimal temperatura) {
        if (temperatura.compareTo(new BigDecimal("35.0")) <= 0) return 3;
        if (temperatura.compareTo(new BigDecimal("36.0")) <= 0) return 1;
        if (temperatura.compareTo(new BigDecimal("38.0")) <= 0) return 0;
        if (temperatura.compareTo(new BigDecimal("39.0")) <= 0) return 1;

        return 2;
    }

    static int pontosPressaoSistolica (int pressao) {
        if (pressao <= 90) return 3;
        if (pressao <= 100) return 2;
        if (pressao <= 110) return 1;
        if (pressao <= 219) return 0;

        return 3;
    }

    static int pontosFrequenciaRespiratoria (int freqResp) {
        if (freqResp <= 8) return 3;
        if (freqResp <= 11) return 1;
        if (freqResp <= 20) return 0;
        if (freqResp <= 24) return 2;

        return 3;
    }
}
