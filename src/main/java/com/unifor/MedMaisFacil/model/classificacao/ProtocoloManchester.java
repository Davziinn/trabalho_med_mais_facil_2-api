package com.unifor.MedMaisFacil.model.classificacao;

import com.unifor.MedMaisFacil.enums.PrioridadeChamado;
import com.unifor.MedMaisFacil.enums.SintomaPrincipal;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

public class ProtocoloManchester {
    private static final Map<String, PrioridadeChamado> DISCRIMINADORES_GERAIS_PRIORIDADE = Map.of(
            "RISCO_MORTE_VIAS_AEREAS", PrioridadeChamado.VERMELHO,
            "ALTERACAO_CONSCIENCIA", PrioridadeChamado.VERMELHO,
            "HEMORRAGIA_NAO_CONTROLADA", PrioridadeChamado.VERMELHO,
            "FEBRE_ALTA_MAL_ESTAR", PrioridadeChamado.LARANJA,
            "AGRAVAMENTO_RAPIDO0", PrioridadeChamado.LARANJA
    );

    private static final Map<SintomaPrincipal, Map<String, PrioridadeChamado>> FLUXOGRAMA_PRIORIDADE = new EnumMap<>(SintomaPrincipal.class);

    static {
        FLUXOGRAMA_PRIORIDADE.put(SintomaPrincipal.DOR_TORACICA, Map.of(
                "SUDORESE_FRIA", PrioridadeChamado.VERMELHO,
                "IRRADIA_BRANCO_MANDIBULA", PrioridadeChamado.LARANJA,
                "NAUSEA_JUNTO", PrioridadeChamado.LARANJA,
                "DOR_MODERADA_ESTAVEL", PrioridadeChamado.AMARELO,
                "DOR_LEVE_CURTA", PrioridadeChamado.VERDE,
                "RELACIONADA_ESFORCO_OU_CONHECIDA", PrioridadeChamado.AZUL
        ));

        FLUXOGRAMA_PRIORIDADE.put(SintomaPrincipal.DISPNEIA, Map.of(
                "INCAPAZ_FALAR_FRASES", PrioridadeChamado.VERMELHO,
                "LABIOS_UNHAS_AROXEADOS", PrioridadeChamado.VERMELHO,
                "FALTA_AR_REPOUSO", PrioridadeChamado.LARANJA,
                "FALTA_AR_SO_ESFORCO", PrioridadeChamado.AMARELO,
                "RELACIONADA_RESFRIADO_GRIPE", PrioridadeChamado.VERDE,
                "FALTA_AR_ANTIGA_CONHECIDA", PrioridadeChamado.AZUL
        ));

        FLUXOGRAMA_PRIORIDADE.put(SintomaPrincipal.FEBRE, Map.of(
                "RIGIDEZ_NUCA_OU_MANCHAS", PrioridadeChamado.VERMELHO,
                "GRUPO_RISCO", PrioridadeChamado.LARANJA,
                "DOR_FORTE_JUNTO_FEBRE", PrioridadeChamado.LARANJA,
                "FEBRE_MAIS_2_DIAS", PrioridadeChamado.AMARELO,
                "TEMPERATURA_38_39_LEVE", PrioridadeChamado.VERDE,
                "TEMPERATURA_ABAIXO_38_BEM", PrioridadeChamado.AZUL
        ));

        FLUXOGRAMA_PRIORIDADE.put(SintomaPrincipal.DOR_ABDOMINAL, Map.of(
                "BARRIGA_DURA_RIGIDA", PrioridadeChamado.VERMELHO,
                "VOMITO_COM_SANGUE", PrioridadeChamado.VERMELHO,
                "GESTANTE", PrioridadeChamado.LARANJA,
                "VOMITO_REPETIDO_OU_FEBRE", PrioridadeChamado.AMARELO,
                "DOR_LEVE_MODERADA_TOLERAVEL", PrioridadeChamado.VERDE,
                "COLICA_CONHECIDA", PrioridadeChamado.AZUL
        ));

        FLUXOGRAMA_PRIORIDADE.put(SintomaPrincipal.CEFALEIA, Map.of(
                "PIOR_DOR_VIDA_SUBITA", PrioridadeChamado.VERMELHO,
                "ALTERACAO_VISUAL_OU_RIGIDEZ_NUCA", PrioridadeChamado.VERMELHO,
                "NAUSE_VOMITO_FORTE", PrioridadeChamado.LARANJA,
                "FEBRE_JUNTO_CEFALEIA", PrioridadeChamado.LARANJA,
                "NAO_MELHORA_ANALGESICO", PrioridadeChamado.AMARELO,
                "PADRAO_CONHECIDO_ENXAQUECA", PrioridadeChamado.VERDE,
                "LEVE_MELHORA_REPOPUSO", PrioridadeChamado.AZUL
        ));
    }

    public static PrioridadeChamado classificar (Map<String, Boolean> discriminadoresGerais, SintomaPrincipal sintoma, Map<String, Object> respostasFluxograma) {
        PrioridadeChamado prioridadeFinal = PrioridadeChamado.AZUL;

        prioridadeFinal = avaliar(discriminadoresGerais, DISCRIMINADORES_GERAIS_PRIORIDADE, prioridadeFinal);

        Map<String, PrioridadeChamado> mapaDoSintoma = FLUXOGRAMA_PRIORIDADE.getOrDefault(sintoma, Map.of());
        Map<String, Boolean> respostaFluxogramaComoBoolean = converterParaBoolean(respostasFluxograma);
        prioridadeFinal = avaliar(respostaFluxogramaComoBoolean, mapaDoSintoma, prioridadeFinal);


        return prioridadeFinal;
    }

    private static Map<String, Boolean> converterParaBoolean(Map<String, Object> respostasFluxograma) {
        Map<String, Boolean> convertido = new HashMap<>();

        for (Map.Entry<String, Object> resposta : respostasFluxograma.entrySet()) {
            Object valor = resposta.getValue();
            if (valor instanceof Boolean valorBooleano) {
                convertido.put(resposta.getKey(), valorBooleano);
            }
        }

        return convertido;
    }

    private static PrioridadeChamado avaliar (Map<String, Boolean> respostas, Map<String, PrioridadeChamado> mapaPrioridade, PrioridadeChamado prioridadeAtual) {
        PrioridadeChamado maisUrgenteEncontrado = prioridadeAtual;

        for (Map.Entry<String, Boolean> resposta : respostas.entrySet()) {
            String chavePergunta = resposta.getKey();
            Boolean valorResposta = resposta.getValue();

            if (Boolean.TRUE.equals(valorResposta)) {
                PrioridadeChamado corDaPergunta = mapaPrioridade.get(chavePergunta);

                if (corDaPergunta != null && corDaPergunta.ordinal() < maisUrgenteEncontrado.ordinal()) {
                    maisUrgenteEncontrado = corDaPergunta;
                }
            }
        }

        return maisUrgenteEncontrado;
    }
}
