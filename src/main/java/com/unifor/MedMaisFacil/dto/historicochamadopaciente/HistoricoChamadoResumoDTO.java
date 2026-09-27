package com.unifor.MedMaisFacil.dto.historicochamadopaciente;

import com.unifor.MedMaisFacil.enums.PrioridadeChamado;
import com.unifor.MedMaisFacil.enums.SintomaPrincipal;
import com.unifor.MedMaisFacil.enums.StatusChamado;

import java.time.LocalDateTime;

public record HistoricoChamadoResumoDTO(
        Long chamadoId,
        SintomaPrincipal sintomaPrincipal,
        LocalDateTime dataCriacao,
        PrioridadeChamado prioridade,
        StatusChamado status
) {
}
