package com.unifor.MedMaisFacil.entity;

import com.unifor.MedMaisFacil.enums.SintomaPrincipal;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "TB_QUEST_SINTOMAS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class QuestionarioSintomasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chamado_id", nullable = false, unique = true)
    private ChamadoEntity chamado;

    @Enumerated(EnumType.STRING)
    @Column(name = "sintoma_principal", nullable = false)
    private SintomaPrincipal sintomaPrincipal;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb", nullable = false)
    private RespostasQuestionario respostas;
}
