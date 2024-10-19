package org.feiraconectada.feiraconectadaapi.repository.financeiro;

import org.feiraconectada.feiraconectadaapi.model.financeiro.PedidoEntidade;
import org.feiraconectada.feiraconectadaapi.model.financeiro.PedidoProdutoEntidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PedidoRepository extends JpaRepository<PedidoEntidade, Long> {

    @Query(nativeQuery = true,
            value = """
                    select
                     	ped.*
                     from
                     	financeiro.ped_pedido ped
                     where
                     	ped.ped_nr_id in (:pedNrIds)
                     and (ped.ped_tx_status = 'CRIADO'
                     and ped.usu_nr_id =:usuNrId)
                    """)
    List<PedidoEntidade> findPedidoStatusCriadoByUsuNrId(
            @Param("pedNrIds") List<Long> pedNrIds,
            @Param("usuNrId") Long usuNrId);

}
