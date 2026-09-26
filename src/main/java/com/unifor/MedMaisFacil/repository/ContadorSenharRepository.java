package com.unifor.MedMaisFacil.repository;

import com.unifor.MedMaisFacil.entity.ContadorSenhaEntity;
import com.unifor.MedMaisFacil.entity.ContadorSenhaPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContadorSenharRepository extends JpaRepository<ContadorSenhaEntity, ContadorSenhaPK> {

    @Query(value = """
            INSERT INTO TB_CONTADOR_SENHA (DATA_CONTAGEM, PREFIXO, CONTADOR)
            VALUES (CURRENT_DATE, :prefixo, 1)
            ON CONFLICT (DATA_CONTAGEM, PREFIXO)
            DO UPDATE SET CONTADOR = TB_CONTADOR_SENHA.CONTADOR + 1
            RETURNING CONTADOR
            """, nativeQuery = true)
    Integer incrementarEBuscarContador (@Param("prefixo") String prefixo);
}
