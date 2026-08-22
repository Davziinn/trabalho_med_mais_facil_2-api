package com.unifor.MedMaisFacil.model;

import com.unifor.MedMaisFacil.entity.ChamadoEntity;
import com.unifor.MedMaisFacil.entity.RespostasQuestionario;
import com.unifor.MedMaisFacil.enums.SintomaPrincipal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class QuestionarioSintomas {

    private Long id;
    private ChamadoEntity chamado;
    private SintomaPrincipal sintomaPrincipal;
    private RespostasQuestionario respostas;
}
