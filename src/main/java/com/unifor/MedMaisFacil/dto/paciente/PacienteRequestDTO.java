package com.unifor.MedMaisFacil.dto.paciente;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record PacienteRequestDTO (

        @NotBlank(message = "O campo NOME é obrigatório")
        String nome,

        @NotNull(message = "O campo DATA DE NASCIMENTO é obrigatória")
        @Past(message = "A data de nascimento deve ser uma data passada")
        LocalDate dataNascimento,
        String sexo,

        @NotBlank(message = "O campo EMAIL é obrigatório")
        @Email(message = "Digite um EMAIL válido")
        String email,

        @NotBlank(message = "O campo CPF é obrigatório")
        @Pattern(
                regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}",
                message = "O CPF deve estar no formato 000.000.000-00"
        )
        String cpf,

        @NotBlank(message = "O campo SENHA é obrigatória")
        @Size(min = 6, message = "A senha deve conter no mínimo 6 caracteres")
        String senha
) {}
