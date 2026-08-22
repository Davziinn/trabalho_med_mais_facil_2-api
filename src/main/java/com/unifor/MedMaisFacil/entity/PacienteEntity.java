package com.unifor.MedMaisFacil.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "TB_PACIENTE")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class PacienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PACIENTE")
    private Long id;

    @Column(name = "NOME_PACIENTE", nullable = false)
    private String nome;

    @Column(name = "DT_NASC_PACIENTE", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "SX_PACIENTE", nullable = false)
    private String sexo;

    @Column(name = "EMAIL_PACIENTE", nullable = false, unique = true)
    private String email;

    @Column(name = "CPF_PACIENTE", nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(name = "SENHA_PACIENTE", nullable = false)
    private String senha;

    @Column(name = "DT_CADASTRO", nullable = false)
    @CreationTimestamp
    private LocalDateTime dataCadastro;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<ChamadoEntity> chamados;
}
