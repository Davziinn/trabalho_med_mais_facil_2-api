package com.unifor.MedMaisFacil.service;

import com.unifor.MedMaisFacil.enums.PrioridadeChamado;
import com.unifor.MedMaisFacil.enums.SintomaPrincipal;
import com.unifor.MedMaisFacil.model.Orientacao;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Service
public class OrientacaoServiceImpl implements OrientacaoService {

    private static final String MENSAGEM_URGENCIA = "Sua classificação indica prioridade de atendimento. Não espere em casa - procure atendimento imediatamente.";

    private static final Map<SintomaPrincipal, List<String>> CUIDADOS_CASA = new EnumMap(SintomaPrincipal.class);
    private static final Map<SintomaPrincipal, List<String>> SINAIS_ALERTA = new EnumMap(SintomaPrincipal.class);

    static {
        CUIDADOS_CASA.put(SintomaPrincipal.CEFALEIA, List.of(
                "Descanse em local silencioso e com pouca luz.",
                "Beba água e evite ficar muito tempo sem comer.",
                "Se a dor durar mais de 2 dias, agende uma consulta."
        ));
        SINAIS_ALERTA.put(SintomaPrincipal.CEFALEIA, List.of(
                "Dor de cabeça súbita e muito forte",
                "Mudança na fala, visão ou força do corpo",
                "Febre com rigidez no pescoço"
        ));

        CUIDADOS_CASA.put(SintomaPrincipal.FEBRE, List.of(
                "Beba bastante líquido e use roupas leves.",
                "Pode usar um antitérmico comum, se não tiver alergia conhecida.",
                "Monitore a temperatura a cada poucas horas."
        ));
        SINAIS_ALERTA.put(SintomaPrincipal.FEBRE, List.of(
                "Febre acima de 39°C que não cede",
                "Manchas roxas na pele ou rigidez no pescoço",
                "Confusão mental ou muita sonolência"
        ));

        CUIDADOS_CASA.put(SintomaPrincipal.DOR_ABDOMINAL, List.of(
                "Evite alimentos pesados ou gordurosos.",
                "Beba água em pequenos goles.",
                "Se for uma cólica conhecida, use o que costuma aliviar."
        ));
        SINAIS_ALERTA.put(SintomaPrincipal.DOR_ABDOMINAL, List.of(
                "Dor que piora muito rapidamente",
                "Vômito com sangue",
                "Barriga muito dura ou rígida ao toque"
        ));

        CUIDADOS_CASA.put(SintomaPrincipal.DISPNEIA, List.of(
                "Fique em posição sentada, evite deitar totalmente.",
                "Evite esforço físico até melhorar.",
                "Se for crise conhecida (asma/bronquite), use a medicação de resgate habitual."
        ));
        SINAIS_ALERTA.put(SintomaPrincipal.DISPNEIA, List.of(
                "Dificuldade para falar frases completas",
                "Lábios ou unhas arroxeados",
                "Piora rápida da falta de ar"
        ));

        CUIDADOS_CASA.put(SintomaPrincipal.DOR_TORACICA, List.of(
                "Fique em repouso, sentado ou deitado com a cabeceira elevada.",
                "Evite esforço físico.",
                "Não dirija até a dor ser avaliada."
        ));
        SINAIS_ALERTA.put(SintomaPrincipal.DOR_TORACICA, List.of(
                "Dor que se espalha para o braço ou mandíbula",
                "Suor frio junto com a dor",
                "Falta de ar junto com a dor"
        ));
    }

    @Override
    public Orientacao buscarOrientacao(SintomaPrincipal sintomaPrincipal, PrioridadeChamado prioridade) {
        List<String> sinaisAlerta = SINAIS_ALERTA.getOrDefault(sintomaPrincipal, List.of());
        boolean urgencia = prioridade == PrioridadeChamado.VERMELHO || prioridade == PrioridadeChamado.LARANJA;

        if (urgencia) {
            return new Orientacao(true, MENSAGEM_URGENCIA, List.of(), sinaisAlerta);
        }

        List<String> cuidadosCasa = CUIDADOS_CASA.getOrDefault(sintomaPrincipal, List.of());

        return new Orientacao(false, null, cuidadosCasa, sinaisAlerta);
    }
}
