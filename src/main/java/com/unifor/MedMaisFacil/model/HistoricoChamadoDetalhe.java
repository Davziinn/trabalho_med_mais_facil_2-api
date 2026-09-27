package com.unifor.MedMaisFacil.model;

import com.unifor.MedMaisFacil.dto.historicochamadopaciente.RespostaDetalheDTO;
import com.unifor.MedMaisFacil.enums.PrioridadeChamado;
import com.unifor.MedMaisFacil.enums.SintomaPrincipal;
import com.unifor.MedMaisFacil.enums.StatusChamado;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class HistoricoChamadoDetalhe {
    private Long chamadoId;
    private SintomaPrincipal sintomaPrincipal;
    private LocalDateTime dataCriacao;
    private PrioridadeChamado prioridadeChamado;
    private StatusChamado statusChamado;
    private List<RespostaDetalheDTO> discriminadoresGerais;
    private List<RespostaDetalheDTO> respostasFluxograma;
}
