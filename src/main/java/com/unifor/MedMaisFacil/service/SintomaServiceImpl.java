package com.unifor.MedMaisFacil.service;

import com.unifor.MedMaisFacil.dto.chamado.PerguntaDTO;
import com.unifor.MedMaisFacil.dto.chamado.QuestionarioPerguntasDTO;
import com.unifor.MedMaisFacil.dto.chamado.SintomaOpcaoDTO;
import com.unifor.MedMaisFacil.enums.SintomaPrincipal;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
public class SintomaServiceImpl implements SintomaService {

    private static final List<PerguntaDTO> DISCRIMINADORES_GERAIS = List.of(
            new PerguntaDTO("RISCO_MORTE_VIAS_AEREAS", "Você está com muita dificuldade para respirar ou sensação de engasgo?"),
            new PerguntaDTO("ALTERACAO_CONSCIENCIA", "Você está confuso(a), muito sonolento(a) ou teve um desmaio?"),
            new PerguntaDTO("HEMORRAGIA_NAO_CONTROLADA", "Você tem um sangramento que não para mesmo fazendo pressão?"),
            new PerguntaDTO("FEBRE_ALTA_MAL_ESTAR", "Você está com febre acima de 39ºC e um mal-estar importante?"),
            new PerguntaDTO("AGRAVAMENTO_RAPIDO", "Seus sintomas pioraram muito nos últimos minutos?")
    );

    private static final Map<SintomaPrincipal, List<PerguntaDTO>> PERGUNTAS_FLUXOGRAMA = new EnumMap<>(SintomaPrincipal.class);

    static {
        PERGUNTAS_FLUXOGRAMA.put(SintomaPrincipal.DOR_TORACICA, List.of(
                new PerguntaDTO("SUDORESE_FRIA", "Está com suor frio junto com a dor?"),
                new PerguntaDTO("IRRADIA_BRANCO_MANDIBULA", "A dor se espalha (irradia) para o braço ou para a mandíbula?"),
                new PerguntaDTO("NAUSEA_JUNTO", "Você sente nausea junto com a dor?"),
                new PerguntaDTO("DOR_MODERADA_ESTAVEL", "A dor é moderada e você se sente estável, sem outros sintomas junto?"),
                new PerguntaDTO("DOR_LEVE_CURTA", "A dor é leve e dura pouco tempo?"),
                new PerguntaDTO("RELACIONADA_ESFORCO_OU_CONHECIDA", "Essa dor estar relacionada a esforço ou é uma dor que você já conhece e já teve antes?")
        ));

        PERGUNTAS_FLUXOGRAMA.put(SintomaPrincipal.DISPNEIA, List.of(
                new PerguntaDTO("INCAPAZ_FALAR_FRASES", "Você consegue falar frases completas ou só consegue falar palavras soltas por falta de ar?"),
                new PerguntaDTO("LABIOS_UNHAS_AROXEADOS", "Seus lábios ou unhas estão arroxeados ou azulados?"),
                new PerguntaDTO("FALTA_AR_REPOUSO", "Você sente falta de ar mesmo parado(a), em repouso?"),
                new PerguntaDTO("FALTA_AR_SO_ESFORCO", "A falta de ar aparece só quando você faz algum esforço, como andar ou subir escada?"),
                new PerguntaDTO("RELACIONADA_RESFRIADO_GRIPE", "Essa falta de ar parece estar relacionada a um resfriado ou gripe leve?"),
                new PerguntaDTO("FALTA_AR_ANTIGA_CONHECIDA", "Essa falta de ar é antiga e você já sabe que tem esse problema?")
        ));

        PERGUNTAS_FLUXOGRAMA.put(SintomaPrincipal.FEBRE, List.of(
                new PerguntaDTO("RIGIDEZ_NUCA_OU_MANCHAS", "Além da febre, seu pescoço está duro (rigidez de nuca) ou apareceram manchas roxas na pele?"),
                new PerguntaDTO("FEBRE_MAIS_2_DIAS", "A febre alta já dura mais de 2 dias?"),
                new PerguntaDTO("GRUPO_RISCO", "Você é bebê, idoso(a) ou está grávida?"),
                new PerguntaDTO("DOR_FORTE_JUNTO_FEBRE", "Tem alguma dor forte junto com a febre?"),
                new PerguntaDTO("TEMPERATURA_38_39_LEVE", "Sua temperatura está entre 38°C e 39°C, com sintomas leves?"),
                new PerguntaDTO("TEMPERATURA_ABAIXO_38_BEM", "Sua temperatura está abaixo de 38°C e você se sente bem, no geral")
        ));

        PERGUNTAS_FLUXOGRAMA.put(SintomaPrincipal.DOR_ABDOMINAL, List.of(
                new PerguntaDTO("BARRIGA_DURA_RIGIDA", "Sua barriga está dura ou rígida ao toque?"),
                new PerguntaDTO("VOMITO_COM_SANGUE", "Você vomitou com sangue?"),
                new PerguntaDTO("GESTANTE", "Você está grávida?"),
                new PerguntaDTO("VOMITO_REPETIDO_OU_FEBRE", "Você está vomitando repetidamente ou tem febre junto com a dor?"),
                new PerguntaDTO("DOR_LEVE_MODERADA_TOLERAVEL", "A dor é leve a moderada e ainda é tolerável?"),
                new PerguntaDTO("COLICA_CONHECIDA", "Essa for parece uma cólica que você já conhece, como cólica menstrual?")
        ));

        PERGUNTAS_FLUXOGRAMA.put(SintomaPrincipal.CEFALEIA, List.of(
                new PerguntaDTO("PIOR_DOR_VIDA_SUBITA", "É a pior dor de cabeça que você já sentiu na vida, e ela começou de repente?"),
                new PerguntaDTO("ALTERACAO_VISUAL_OU_RIGIDEZ_NUCA", "Você está com alteração na visão ou rigidez no pescoço junto com a dor?"),
                new PerguntaDTO("NAUSE_VOMITO_FORTE", "Você tem náusea ou vômito forte junto com a dor de cabeça?"),
                new PerguntaDTO("NAO_MELHORA_ANALGESICO", "A dor continua mesmo depois de tomar um analgésico comum?"),
                new PerguntaDTO("FEBRE_JUNTO_CEFALEIA", "Você está com febre junto com a dor de cabeça?"),
                new PerguntaDTO("PADRAO_CONHECIDO_ENXAQUECA", "Essa dor de cabeça segue um padrão que você já conhece, como enxaqueca?"),
                new PerguntaDTO("LEVE_MELHORA_REPOPUSO", "A dor é leve e melhora com repouso")
        ));
    }

    @Override
    public List<SintomaOpcaoDTO> listarSintomas() {
        return Arrays.stream(SintomaPrincipal.values())
                .map(sintoma -> new SintomaOpcaoDTO(sintoma.name(), sintoma.getLabel()))
                .toList();
    }

    @Override
    public QuestionarioPerguntasDTO listarPerguntas(SintomaPrincipal sintoma) {
        List<PerguntaDTO> perguntasFluxograma = PERGUNTAS_FLUXOGRAMA.getOrDefault(sintoma, List.of());
        return new QuestionarioPerguntasDTO(DISCRIMINADORES_GERAIS, perguntasFluxograma);
    }
}
