package com.unifor.MedMaisFacil.controller;

import com.unifor.MedMaisFacil.dto.historicochamadopaciente.HistoricoChamadoDetalheDTO;
import com.unifor.MedMaisFacil.dto.historicochamadopaciente.HistoricoChamadoResumoDTO;
import com.unifor.MedMaisFacil.dto.paciente.PacienteRequestDTO;
import com.unifor.MedMaisFacil.dto.paciente.CadastroPacienteResponseDTO;
import com.unifor.MedMaisFacil.dto.paciente.PacienteResponseDTO;
import com.unifor.MedMaisFacil.mapper.HistoricoMapper;
import com.unifor.MedMaisFacil.mapper.PacienteMapper;
import com.unifor.MedMaisFacil.model.HistoricoChamadoDetalhe;
import com.unifor.MedMaisFacil.model.HistoricoChamadoResumo;
import com.unifor.MedMaisFacil.model.Paciente;
import com.unifor.MedMaisFacil.service.HistoricoService;
import com.unifor.MedMaisFacil.service.PacienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/paciente")
@RequiredArgsConstructor
public class PacienteControllerImpl implements PacienteController{

    private final PacienteService pacienteService;
    private final PacienteMapper pacienteMapper;

    private final HistoricoService historicoService;
    private final HistoricoMapper historicoMapper;

    @Override
    @PostMapping
    public ResponseEntity<CadastroPacienteResponseDTO> cadastrarPaciente(@Valid @RequestBody PacienteRequestDTO dto) {
        Paciente pacienteCadastrado = pacienteService.cadastrar(pacienteMapper.toModel(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteMapper.toCadastroDTO(pacienteCadastrado));
    }

    @Override
    @GetMapping("/{cpf}")
    public ResponseEntity<PacienteResponseDTO> identificacaoPaciente (@PathVariable String cpf) {
        Paciente pacienteIdentificado = pacienteService.buscarPacienteByCpf(cpf);
        return ResponseEntity.ok(pacienteMapper.toDTO(pacienteIdentificado));
    }

    @Override
    @GetMapping("/{pacienteId}/historico")
    public ResponseEntity<List<HistoricoChamadoResumoDTO>> listarHistorico (@PathVariable Long pacienteId) {
        List<HistoricoChamadoResumo> historicoListado = historicoService.listarHistorico(pacienteId);
        return ResponseEntity.ok(historicoListado.stream().map(historicoMapper::toResumoDTO).toList());
    }

    @GetMapping("/{pacienteId}/historico/{chamadoId}")
    public ResponseEntity<HistoricoChamadoDetalheDTO> detalharHistorico (@PathVariable Long pacienteId, @PathVariable Long chamadoId) {
        HistoricoChamadoDetalhe historicoDetalhe = historicoService.detalharHistorico(pacienteId, chamadoId);
        return ResponseEntity.ok(historicoMapper.toDetalheDTO(historicoDetalhe));
    }
}
