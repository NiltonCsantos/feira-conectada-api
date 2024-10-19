package org.feiraconectada.feiraconectadaapi.repository.financeiro;

import org.feiraconectada.feiraconectadaapi.model.financeiro.PedidoProdutoEntidade;
import org.feiraconectada.feiraconectadaapi.service.financeiro.pedidoproduto.dto.PedidoProdutoDadosCompletosDto;
import org.feiraconectada.feiraconectadaapi.service.financeiro.pedidoproduto.form.PedidoProdutoFiltroForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PedidoProdutoRepository extends JpaRepository<PedidoProdutoEntidade, Long> {

    @Query(nativeQuery = true,
            value = """

                    SELECT
    ven.ven_nr_id AS venNrId,
    usu.usu_tx_nome AS usuTxNome,
    ven.ven_nr_id AS venNrLoja,

    ped.ped_dt_criado  datePedDtCriado,
    json_build_object(
        'PedNrId', ped.ped_nr_id,
        'PedTxStatus', ped.ped_tx_status,
        'PedNrValorTotal', ped.ped_nr_valor_total,
        'PedidoProdutos', json_agg(
            json_build_object(
                'PpNrId', pp.pp_nr_id,
                'PpNrPreco', pp.pp_nr_preco,
                'PpNrQuantidadeProduto', pp.pp_nr_quantidade_produto,
                'ProNrId', pro.pro_nr_id,
                'ProNrPreco', pro.pro_nr_preco,
          
                'ProTxNome', pro.pro_tx_nome
            )
        )
    ) AS Pedido
FROM
    financeiro.pp_pedido_produto pp
INNER JOIN financeiro.ped_pedido ped ON
    ped.ped_nr_id = pp.ped_nr_id
INNER JOIN financeiro.pro_produto pro ON
    pro.pro_nr_id = pp.pro_nr_id
LEFT JOIN financeiro.ip_imagem_produto ip ON
    ip.ip_nr_id = pro.ip_nr_id
INNER JOIN financeiro.est_estoque est ON
    est.est_nr_id = pro.est_nr_id
INNER JOIN financeiro.ven_vendedor ven ON
    ven.ven_nr_id = est.ven_nr_id
LEFT JOIN financeiro.iv_imagem_vendedor iv ON
    iv.iv_nr_id = ven.iv_nr_id
INNER JOIN autenticacao.usu_usuario usu ON
    usu.usu_nr_id = ven.ven_nr_id
WHERE
    ped.usu_nr_id =:usuNrId
     and (:#{#filtro.pedTxStatus()==null} or upper(ped.ped_tx_status) = upper(:#{#filtro.pedTxStatus()?.name()}))
GROUP BY\s
    ven.ven_nr_id,\s
    usu.usu_tx_nome,\s
    ped.ped_nr_id, \s
    iv.iv_tx_imagem
ORDER BY ped.ped_nr_id;
                    """)
    Page<PedidoProdutoDadosCompletosDto> findAllByUsuNrId(@Param("usuNrId") Long usuNrId, PedidoProdutoFiltroForm filtro, Pageable pageable);
    List<PedidoProdutoEntidade> findAllByPedNrId(@Param("pedNrId") Long pedNrId);
}
