package com.unifor.MedMaisFacil.entity;

import com.unifor.MedMaisFacil.enums.PrioridadeChamado;
import com.unifor.MedMaisFacil.enums.StatusChamado;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "TB_CHAMADO")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class ChamadoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CHAMADO")
    private Long id;

    @Column(name = "SENHA_FILA", nullable = false, unique = true)
    private String senhaFila;

    @Enumerated(EnumType.STRING)
    @Column(name = "STS_CHAMADO", nullable = false)
    private StatusChamado statusChamado;

    @Enumerated(EnumType.STRING)
    @Column(name = "PRIO_CHAMADO")
    private PrioridadeChamado prioridadeChamado;

    @CreationTimestamp
    @Column(name = "DT_CRI")
    private LocalDateTime dataCriacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    private PacienteEntity paciente;

    @OneToOne(mappedBy = "chamado", cascade = CascadeType.ALL)
    private SinaisVitaisEntity sinaisVitais;

    @OneToOne(mappedBy = "chamado", cascade = CascadeType.ALL)
    private QuestionarioSintomasEntity questionarioSintomas;
}
