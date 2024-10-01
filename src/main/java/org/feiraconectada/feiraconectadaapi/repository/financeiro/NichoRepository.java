package org.feiraconectada.feiraconectadaapi.repository.financeiro;

import feign.Param;
import org.feiraconectada.feiraconectadaapi.model.financeiro.NichoEntidade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NichoRepository extends JpaRepository<NichoEntidade, Long> {

    @Query(nativeQuery = true,
            value = """
                     select nic.* from financeiro.nic_nicho nic
                         inner join financeiro.est_estoque est on est.nic_nr_id = nic.nic_nr_id\s
                         inner join financeiro.ven_vendedor ven on ven.ven_nr_id = est.ven_nr_id\s
                         where ven.ven_nr_id =:venNrId
                    """
    )
    Page<NichoEntidade> listarNichosDosEstoquesPoridDoVendedor(@Param("venNrId") Long venNrId, Pageable pageable);

}
