package com.unifor.MedMaisFacil.controller;

import com.unifor.MedMaisFacil.entity.*;
import com.unifor.MedMaisFacil.enums.*;
import com.unifor.MedMaisFacil.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EncaminhamentoControllerTest {
    private final UnidadeSaudeRepository unidades = mock(UnidadeSaudeRepository.class);
    private final ChamadoRepository chamados = mock(ChamadoRepository.class);
    private final EncaminhamentoController controller = new EncaminhamentoController(unidades, chamados);

    private UnidadeSaudeEntity hospital(long id, double lat, SintomaPrincipal sintoma) {
        return UnidadeSaudeEntity.builder().id(id).nome("Hospital " + id).endereco("Endereço")
                .latitude(lat).longitude(0.0).sintomasAtendidos(List.of(sintoma)).build();
    }

    private ChamadoEntity chamado() {
        return ChamadoEntity.builder().id(7L).paciente(PacienteEntity.builder().id(1L).build())
                .statusChamado(StatusChamado.AGUARDANDO_TRIAGEM)
                .questionarioSintomas(QuestionarioSintomasEntity.builder().sintomaPrincipal(SintomaPrincipal.FEBRE).build()).build();
    }

    @Test void listaPorProximidadeFiltrandoSintoma() {
        when(unidades.findAll()).thenReturn(List.of(hospital(1, 3, SintomaPrincipal.FEBRE),
                hospital(2, 1, SintomaPrincipal.FEBRE), hospital(3, 0, SintomaPrincipal.CEFALEIA)));
        var resultado = controller.listar(SintomaPrincipal.FEBRE, 0.0, 0.0);
        assertEquals(List.of(2L, 1L), resultado.stream().map(EncaminhamentoController.Hospital::id).toList());
        assertEquals(111.195, resultado.getFirst().distanciaKm(), 0.001);
    }

    @Test void permiteListarSemLocalizacao() {
        when(unidades.findAll()).thenReturn(List.of(hospital(1, 0, SintomaPrincipal.FEBRE)));
        assertNull(controller.listar(SintomaPrincipal.FEBRE, null, null).getFirst().distanciaKm());
        assertThrows(ResponseStatusException.class, () -> controller.listar(SintomaPrincipal.FEBRE, 91.0, 0.0));
        assertThrows(ResponseStatusException.class, () -> controller.listar(SintomaPrincipal.FEBRE, 0.0, null));
    }

    @Test void salvaHospitalAntesDeGerarCodigoSemEntrarNaFila() {
        var chamado = chamado();
        var hospital = hospital(2, 1, SintomaPrincipal.FEBRE);
        when(chamados.findById(7L)).thenReturn(Optional.of(chamado));
        when(unidades.findById(2L)).thenReturn(Optional.of(hospital));
        var result = controller.selecionar(1L, 7L, new EncaminhamentoController.Selecao(2L));
        verify(chamados).save(chamado);
        assertSame(hospital, chamado.getUnidadeSaude());
        assertEquals(StatusChamado.AGUARDANDO_TRIAGEM, chamado.getStatusChamado());
        assertEquals("medmaisfacil:checkin:v1:7:2", result.qrCode());
        assertEquals(result, controller.selecionar(1L, 7L, new EncaminhamentoController.Selecao(2L)));
    }

    @Test void rejeitaOutroPacienteHospitalIncompativelEChamadoFinalizado() {
        var chamado = chamado();
        when(chamados.findById(7L)).thenReturn(Optional.of(chamado));
        when(unidades.findById(2L)).thenReturn(Optional.of(hospital(2, 0, SintomaPrincipal.CEFALEIA)));
        assertThrows(ResponseStatusException.class, () -> controller.selecionar(9L, 7L, new EncaminhamentoController.Selecao(2L)));
        assertThrows(ResponseStatusException.class, () -> controller.selecionar(1L, 7L, new EncaminhamentoController.Selecao(2L)));
        chamado.setStatusChamado(StatusChamado.EM_FILA);
        assertThrows(ResponseStatusException.class, () -> controller.selecionar(1L, 7L, new EncaminhamentoController.Selecao(2L)));
        verify(chamados, never()).save(any());
    }
}
