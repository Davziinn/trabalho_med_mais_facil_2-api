package com.unifor.MedMaisFacil.service;

import com.unifor.MedMaisFacil.enums.StatusChamado;
import com.unifor.MedMaisFacil.model.Chamado;

import java.util.List;

public interface ChamadoService {

    Chamado criarChamado (Long pacienteId, Chamado chamado);

    List<Chamado> buscarChamadoByPacienteIdAndStatus (Long pacienteId);

    Chamado buscarChamadoById (Long chamadoId);

    Chamado salvarAlteracoes(Chamado dadosChamado);

    void deletarTudo(List<Chamado> chamado);

    void expirarChamadosAntigosNaoUrgentes(Long pacienteId);

    List<Chamado> buscarPacienteByIdEByStatusOrdenandoByDataDecrescente(Long pacienteId, List<StatusChamado> status);
}
