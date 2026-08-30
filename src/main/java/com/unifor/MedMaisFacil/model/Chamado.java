package com.unifor.MedMaisFacil.model;

import com.unifor.MedMaisFacil.enums.PrioridadeChamado;
import com.unifor.MedMaisFacil.enums.SintomaPrincipal;
import com.unifor.MedMaisFacil.enums.StatusChamado;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Chamado {

    private Long id;
    private String senhaFila;
    private StatusChamado statusChamado;
    private PrioridadeChamado prioridadeChamado;
    private LocalDateTime dataCriacao;
    private Paciente paciente;
    private SinaisVitais sinaisVitais;
    private QuestionarioSintomas questionarioSintomas;

    private SintomaPrincipal sintomaPrincipal;
    private Map<String, Boolean> discriminadoresGerais;
    private Map<String, Object> respostasFluxograma;
}
