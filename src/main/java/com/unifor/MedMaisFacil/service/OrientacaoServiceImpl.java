package com.unifor.MedMaisFacil.service;

import com.unifor.MedMaisFacil.enums.SintomaPrincipal;
import com.unifor.MedMaisFacil.model.Orientacao;
import org.springframework.stereotype.Service;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Service
public class OrientacaoServiceImpl implements OrientacaoService {

    private static final Map<SintomaPrincipal, Orientacao> ORIENTACOES = new EnumMap<>(SintomaPrincipal.class);

    static {
        ORIENTACOES.put(SintomaPrincipal.CEFALEIA, new Orientacao(
                List.of(
                        "Descanse em local silencioso e com pouca luz.",
                        "Beba água e evite ficar muito tempo sem comer.",
                        "Se a dor durar mais de 2 dias, agende uma consulta."
                ),
                List.of(
                        "Dor de cabeça súbita e muito forte",
                        "Mudança na fala, visão ou força do corpo",
                        "Febre com rigidez no pescoço"
                )
        ));

        ORIENTACOES.put(SintomaPrincipal.FEBRE, new Orientacao(
                List.of(
                        "Beba bastante líquido e use roupas leves.",
                        "Pode usar um antitérmico comum, se não tiver alergia conhecida.",
                        "Monitore a temperatura a cada poucas horas."
                ),
                List.of(
                        "Febre acima de 39ºC que não cede",
                        "Manchas roxas na pele ou rígidez no pescoço",
                        "Confusão mental ou muita sonolência"
                )
        ));

        ORIENTACOES.put(SintomaPrincipal.DOR_ABDOMINAL, new Orientacao(
                List.of(
                        "Evite alimentos pesados ou gordurosos.",
                        "Beba água em pequenos goles.",
                        "Se for uma cólica conhecida, use o que costuma aliviar."
                ),
                List.of(
                        "Dor que piora muito rapidamente",
                        "Vômito com sangue",
                        "Barriga muito dura ou rígida ao toque"
                )
        ));

        ORIENTACOES.put(SintomaPrincipal.DISPNEIA, new Orientacao(
                List.of(
                        "Fique em posição sentada, evite deitar totalmente.",
                        "Eviste esforço físico até melhorar.",
                        "Se for crise conhecida (asma/broquite), use a medicação de resgate habitual."
                ),
                List.of(
                        "Dificuldade para falar frases completas",
                        "Lábios ou unhas arroxeados",
                        "Piora rápida de falta de ar"
                )
        ));

        ORIENTACOES.put(SintomaPrincipal.DOR_TORACICA, new Orientacao(
                List.of(
                        "Fique em repouso, sentado ou deitado com a cabeça elevada.",
                        "Evite esforço físico.",
                        "Não dirija até a dor ser aliviada."
                ),
                List.of(
                        "Dor que se espalha para o braço ou mandíbula",
                        "Suor frio junto com a dor",
                        "Falta de ar junto com a dor"
                )
        ));
    }

    @Override
    public Orientacao buscarOrientacao(SintomaPrincipal sintomaPrincipal) {
        return ORIENTACOES.getOrDefault(sintomaPrincipal, new Orientacao(List.of(), List.of()));
    }
}
