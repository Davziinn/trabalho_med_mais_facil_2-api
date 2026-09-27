package com.unifor.MedMaisFacil.dto.historicochamadopaciente;

import com.unifor.MedMaisFacil.enums.PrioridadeChamado;
import com.unifor.MedMaisFacil.enums.SintomaPrincipal;
import com.unifor.MedMaisFacil.enums.StatusChamado;

import java.time.LocalDateTime;
import java.util.List;

public record HistoricoChamadoDetalheDTO(
        Long chamadoId,
        SintomaPrincipal sintomaPrincipal,
        LocalDateTime dataCriacao,
        PrioridadeChamado prioridadeChamado,
        StatusChamado statusChamado,
        List<RespostaDetalheDTO> discriminadoresGerais,
        List<RespostaDetalheDTO> respostasFluxograma
) {
}
