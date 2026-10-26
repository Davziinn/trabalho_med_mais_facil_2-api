package com.unifor.MedMaisFacil.controller;

import com.unifor.MedMaisFacil.enums.SintomaPrincipal;
import com.unifor.MedMaisFacil.enums.StatusChamado;
import com.unifor.MedMaisFacil.repository.ChamadoRepository;
import com.unifor.MedMaisFacil.repository.UnidadeSaudeRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.Comparator;
import java.util.List;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class EncaminhamentoController {
    private final UnidadeSaudeRepository unidades;
    private final ChamadoRepository chamados;
    @Value("${medmaisfacil.lotacao.janela-horas:3}")
    private int janelaLotacaoHoras;

    public record Hospital(Long id, String nome, String endereco, Double distanciaKm,
                           Integer ocupacaoAtual, Integer limiteLotacao,
                           Integer ocupacaoPercentual, Boolean lotada) {}
    public record Selecao(@NotNull Long unidadeSaudeId) {}
    public record CheckIn(Long chamadoId, Hospital hospital, String qrCode) {}

    @GetMapping("/v1/unidades-saude")
    @Transactional(readOnly = true)
    public List<Hospital> listar(@RequestParam SintomaPrincipal sintoma,
                                @RequestParam(required = false) Double latitude,
                                @RequestParam(required = false) Double longitude) {
        if ((latitude == null) != (longitude == null) ||
                (latitude != null && (!Double.isFinite(latitude) || !Double.isFinite(longitude) ||
                        Math.abs(latitude) > 90 || Math.abs(longitude) > 180))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Coordenadas inválidas");
        }
        return unidades.findAll().stream()
                .filter(u -> u.getSintomasAtendidos() != null && u.getSintomasAtendidos().contains(sintoma))
                .map(u -> {
                    int ocupacaoAtual = (int) chamados.countByUnidadeSaude_IdAndStatusChamadoAndDataCheckinAfter(
                            u.getId(), StatusChamado.EM_FILA, LocalDateTime.now().minusHours(janelaLotacaoHoras));
                    Integer limite = u.getLimiteLotacao();
                    Integer percentual = limite == null || limite <= 0 ? null : (int) Math.round(ocupacaoAtual * 100.0 / limite);
                    boolean lotada = percentual != null && ocupacaoAtual >= limite;
                    Double distanciaKm = latitude == null || u.getLatitude() == null || u.getLongitude() == null ? null :
                            distancia(latitude, longitude, u.getLatitude(), u.getLongitude());
                    return new Hospital(u.getId(), u.getNome(), u.getEndereco(), distanciaKm,
                            ocupacaoAtual, limite, percentual, lotada);
                })
                .sorted(Comparator.comparing(Hospital::distanciaKm, Comparator.nullsLast(Double::compareTo))
                        .thenComparing(Hospital::nome))
                .toList();
    }

    @PutMapping("/v1/paciente/{pacienteId}/chamado/{chamadoId}/encaminhamento")
    @Transactional
    public CheckIn selecionar(@PathVariable Long pacienteId, @PathVariable Long chamadoId,
                              @Valid @RequestBody Selecao selecao) {
        var chamado = chamados.findById(chamadoId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Chamado não encontrado"));
        if (!chamado.getPaciente().getId().equals(pacienteId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Chamado não encontrado");
        }
        if (chamado.getStatusChamado() != StatusChamado.AGUARDANDO_TRIAGEM) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Chamado não está aguardando triagem");
        }
        var unidade = unidades.findById(selecao.unidadeSaudeId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Hospital não encontrado"));
        if (unidade.getSintomasAtendidos() == null || !unidade.getSintomasAtendidos()
                .contains(chamado.getQuestionarioSintomas().getSintomaPrincipal())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Hospital não atende este sintoma");
        }
        chamado.setUnidadeSaude(unidade);
        chamados.save(chamado);
        // Identificador de apresentação. A entrada na fila continua sendo confirmada no totem.
        return new CheckIn(chamadoId, new Hospital(unidade.getId(), unidade.getNome(), unidade.getEndereco(), null,
                null, unidade.getLimiteLotacao(), null, null),
                "medmaisfacil:checkin:v1:" + chamadoId + ":" + unidade.getId());
    }

    static double distancia(double lat, double lon, double destinoLat, double destinoLon) {
        double a = Math.pow(Math.sin(Math.toRadians(destinoLat - lat) / 2), 2)
                + Math.cos(Math.toRadians(lat)) * Math.cos(Math.toRadians(destinoLat))
                * Math.pow(Math.sin(Math.toRadians(destinoLon - lon) / 2), 2);
        return 6371 * 2 * Math.asin(Math.sqrt(Math.min(1, Math.max(0, a))));
    }
}
