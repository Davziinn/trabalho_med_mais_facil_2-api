package com.unifor.MedMaisFacil.model.classificacao;

import com.unifor.MedMaisFacil.enums.PrioridadeChamado;
import com.unifor.MedMaisFacil.enums.SintomaPrincipal;
import com.unifor.MedMaisFacil.model.SinaisVitais;
import com.unifor.MedMaisFacil.utils.MetodosUtil;

import java.util.EnumSet;
import java.util.Set;

public class AvaliacaoSinaisVitais {

    private static final int PAS_CRISE_HIPERTENSIVA = 180;
    private static final int PAD_CRISE_HIPERTENSIVA = 120;

    private static final Set<SintomaPrincipal> SINTOMAS_AGRAVADOS_POR_PRESSAO_ALTA = EnumSet.of(
            SintomaPrincipal.DOR_TORACICA,
            SintomaPrincipal.CEFALEIA,
            SintomaPrincipal.DISPNEIA
    );

    public static PrioridadeChamado prioridadeMinima (SinaisVitais sinais, SintomaPrincipal sintoma) {
        PrioridadeChamado prioridade = prioridadePorNews2(PontuacaoNews2.calcular(sinais));

        if(temCriseHipertensiva(sinais)) {
            PrioridadeChamado porPressao = SINTOMAS_AGRAVADOS_POR_PRESSAO_ALTA.contains(sintoma)
                    ? PrioridadeChamado.LARANJA
                    : PrioridadeChamado.AMARELO;

            prioridade = escalar(prioridade, porPressao);
        }

        return prioridade;
    }

    public static PrioridadeChamado escalar (PrioridadeChamado atual, PrioridadeChamado minima) {
        if (atual == null) return minima;
        if (minima == null) return atual;
        return minima.ordinal() < atual.ordinal() ? minima : atual;
    }

    private static PrioridadeChamado prioridadePorNews2(PontuacaoNews2.Resultado resultado) {

        if (resultado.pontuacaoTotal() >= 7) return PrioridadeChamado.VERMELHO;
        if (resultado.pontuacaoTotal() >= 5 || resultado.maiorPontuacaoIndividual() == 3) return PrioridadeChamado.LARANJA;
        if (resultado.pontuacaoTotal() >= 3) return PrioridadeChamado.AMARELO;

        return null;
    }

    private static boolean temCriseHipertensiva (SinaisVitais sinais) {
        return MetodosUtil.extrairSistolica(sinais.getPressaoArterial()) >= PAS_CRISE_HIPERTENSIVA || MetodosUtil.extrairDiastolica(sinais.getPressaoArterial()) >= PAD_CRISE_HIPERTENSIVA;
    }
}
