package com.unifor.MedMaisFacil.mapper;

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

        List<Chamado> chamados = entity.getChamados().stream()
                .map(chamadoMapper::toModel)
                .toList();

        return Paciente.builder()
                .id(entity.getId())
                .cpf(entity.getCpf())
                .nome(entity.getNome())
                .senha(entity.getSenha())
                .email(entity.getEmail())
                .dataCadastro(entity.getDataCadastro())
                .dataNascimento(entity.getDataNascimento())
                .chamados(chamados)
                .build();
    }

    @Override
    public PacienteEntity toEntity(Paciente model) {
        List<ChamadoEntity> chamados = model.getChamados().stream()
                .map(chamadoMapper::toEntity)
                .toList();

        return PacienteEntity.builder()
                .id(model.getId())
                .cpf(model.getCpf())
                .nome(model.getNome())
                .senha(model.getSenha())
                .email(model.getEmail())
                .dataCadastro(model.getDataCadastro())
                .dataNascimento(model.getDataNascimento())
                .chamados(chamados)
                .build();
    }
}
