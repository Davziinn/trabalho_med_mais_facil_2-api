package com.unifor.MedMaisFacil.mapper;

import com.unifor.MedMaisFacil.dto.paciente.PacienteRequestDTO;
import com.unifor.MedMaisFacil.dto.paciente.PacienteResponseDTO;
import com.unifor.MedMaisFacil.entity.ChamadoEntity;
import com.unifor.MedMaisFacil.entity.PacienteEntity;
import com.unifor.MedMaisFacil.model.Chamado;
import com.unifor.MedMaisFacil.model.Paciente;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PacienteMapperImpl implements PacienteMapper {

    private ChamadoMapper chamadoMapper;

    @Override
    public Paciente toModel(PacienteEntity entity) {
        Paciente model = Paciente.builder()
                .id(entity.getId())
                .cpf(entity.getCpf())
                .nome(entity.getNome())
                .senha(entity.getSenha())
                .email(entity.getEmail())
                .dataCadastro(entity.getDataCadastro())
                .dataNascimento(entity.getDataNascimento())
                .build();


        if (entity.getChamados() != null && !entity.getChamados().isEmpty()) {
            List<Chamado> chamados = entity.getChamados().stream()
                    .map(chamadoMapper::toModel)
                    .toList();

            model = model.toBuilder()
                    .chamados(chamados)
                    .build();
        }

        return model;
    }

    @Override
    public PacienteEntity toEntity(Paciente model) {
        PacienteEntity entity = PacienteEntity.builder()
                .id(model.getId())
                .cpf(model.getCpf())
                .nome(model.getNome())
                .senha(model.getSenha())
                .email(model.getEmail())
                .sexo(model.getSexo())
                .dataCadastro(model.getDataCadastro())
                .dataNascimento(model.getDataNascimento())
                .build();


        if (model.getChamados() != null && !model.getChamados().isEmpty()) {
            List<ChamadoEntity> chamados = model.getChamados().stream()
                    .map(chamadoMapper::toEntity)
                    .toList();

            entity = entity.toBuilder().chamados(chamados).build();
        }

        return entity;
    }

    @Override
    public Paciente toModel(PacienteRequestDTO dto) {
        return Paciente.builder()
                .cpf(dto.cpf())
                .nome(dto.nome())
                .senha(dto.senha())
                .email(dto.email())
                .sexo(dto.sexo())
                .dataNascimento(dto.dataNascimento())
                .build();
    }

    @Override
    public PacienteResponseDTO toDTO(Paciente model) {
        return new PacienteResponseDTO(
                model.getId(),
                model.getNome(),
                model.getEmail()
        );
    }
}
