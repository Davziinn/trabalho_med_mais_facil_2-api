package com.unifor.MedMaisFacil.model;

import com.unifor.MedMaisFacil.enums.PrioridadeChamado;
import com.unifor.MedMaisFacil.enums.SintomaPrincipal;
import com.unifor.MedMaisFacil.enums.StatusChamado;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class HistoricoChamadoResumo {

    private Long chamadoId;
    private SintomaPrincipal sintomaPrincipal;
    private LocalDateTime dataCriacao;
    private PrioridadeChamado prioridade;
    private StatusChamado status;
}
