package com.unifor.MedMaisFacil.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_SINAIS_VITAIS")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class SinaisVitaisEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "PRES_ART")
    private String pressaoArterial;

    @Column(name = "TEMP_CORP")
    private BigDecimal temperaturaCorporal;

    @Column(name = "FC_BPM")
    private Integer frequenciaCardiaca;

    @Column(name = "FREQ_RESP")
    private Integer frequenciaRespiratoria;

    @Column(name = "SAT_OXI")
    private Integer saturacaoO2;

    @CreationTimestamp
    @Column(name = "DT_CAPT_DADOS")
    private LocalDateTime capturadoEm;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chamado_id", nullable = false, unique = true)
    private ChamadoEntity chamado;
}
