package com.unifor.MedMaisFacil.service;

import com.unifor.MedMaisFacil.exceptions.PacienteNotFoundException;
import com.unifor.MedMaisFacil.mapper.PacienteMapper;
import com.unifor.MedMaisFacil.model.Paciente;
import com.unifor.MedMaisFacil.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;
    private final PacienteMapper pacienteMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public Paciente cadastrar(Paciente paciente) {
        Paciente pacienteParaCadastrar = paciente.toBuilder()
                .dataCadastro(LocalDateTime.now())
                .senha(passwordEncoder.encode(paciente.getSenha()))
                .build();

        return pacienteMapper.toModel(pacienteRepository.save(pacienteMapper.toEntity(pacienteParaCadastrar)));
    }

    @Override
    public Paciente buscarPacienteById(Long id) {
        return pacienteMapper.toModel(pacienteRepository.findById(id).orElseThrow(
                () -> new PacienteNotFoundException("Paciente não encontrado")
        ));
    }

    @Override
    public Paciente buscarPacienteByCpf(String cpf) {
        return pacienteMapper.toModel(pacienteRepository.findByCpf(cpf).orElseThrow(
                () -> new PacienteNotFoundException("Paciente não encontrado")
        ));
    }
}
