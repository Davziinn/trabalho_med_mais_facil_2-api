package com.unifor.MedMaisFacil.service;

import com.unifor.MedMaisFacil.enums.PrioridadeChamado;
import com.unifor.MedMaisFacil.repository.ContadorSenharRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class SenhaFilaServiceImpl implements SenhaFilaService {

    private final ContadorSenharRepository contadorSenharRepository;

    private static final Map<PrioridadeChamado, Character> LETRA_POR_PRIORIDADE = Map.of(
            PrioridadeChamado.VERMELHO, 'V',
            PrioridadeChamado.LARANJA, 'L',
            PrioridadeChamado.AMARELO, 'A',
            PrioridadeChamado.VERDE, 'R',
            PrioridadeChamado.AZUL, 'Z'
    );

    @Override
    public String gerarSenhaFila(PrioridadeChamado prioridadeChamado) {
        char letraDaPrioridade = LETRA_POR_PRIORIDADE.get(prioridadeChamado);
        Integer contador = contadorSenharRepository.incrementarEBuscarContador(String.valueOf(letraDaPrioridade));
        return letraDaPrioridade + String.format("%03d", contador);
    }
}
