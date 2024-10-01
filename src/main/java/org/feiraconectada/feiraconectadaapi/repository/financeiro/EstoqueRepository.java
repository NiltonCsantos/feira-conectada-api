package org.feiraconectada.feiraconectadaapi.repository.financeiro;

import org.feiraconectada.feiraconectadaapi.model.financeiro.EstoqueEntidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstoqueRepository extends JpaRepository<EstoqueEntidade, Long> {

    @Query(nativeQuery = true,
            value = """
                    SELECT est.* from financeiro.est_estoque est
                    WHERE (est.est_tx_nicho = :estTxNicho
                            and est.ven_nr_id = :venNrId)   
                    """)
    Optional<EstoqueEntidade> findEstoquePorVendedor(@Param("venNrId") Long venNrId, @Param("estTxNicho") String estTxNicho);

    @Query(nativeQuery = true,
            value = """
                    SELECT est.* from financeiro.est_estoque est
                    WHERE  est.nic_nr_id =:nicNrId  
                    """)
    Page<EstoqueEntidade> listarEstoquesPorNicho(@Param("nicNrId") Long nicNrId, Pageable pageable);

}
