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
                    select
                    pp.pp_nr_id PpNrId,
                    pp.pp_dt_criado PpDtCriado,
                    pp.pp_tx_status PpTxStatus,
                    pp.pp_nr_preco PpNrPreco,
                    pp.pp_nr_quantidade_produto ppNrQuantidadeProduto,
                    pro.pro_nr_id ProNrId,
                    pro.pro_nr_preco proNrPreco,
                    pro.pro_tx_nome proTxNome,
                    ip.ip_tx_imagem IpTxImagem,
                    ven.ven_nr_id VenNrid,
                    iv.iv_tx_imagem IvTxImagem,
                    usu.usu_tx_nome venTxNome
                    from financeiro.pp_pedido_produto pp
                    inner join financeiro.pro_produto pro on pro.pro_nr_id = pp.pro_nr_id
                    left  join financeiro.ip_imagem_produto ip on ip.ip_nr_id = pro.ip_nr_id
                    inner join financeiro.est_estoque est on est.est_nr_id = pro.est_nr_id
                    inner join financeiro.ven_vendedor ven on ven.ven_nr_id = est.ven_nr_id
                    left join financeiro.iv_imagem_vendedor iv on iv.iv_nr_id = ven.iv_nr_id
                    inner join autenticacao.usu_usuario usu on usu.usu_nr_id = ven.ven_nr_id
                    where usu.usu_nr_id =:usuNrId
                    and (:#{#filtro.ppTxStatus()==null} or upper(pp.pp_tx_status) = upper(:#{#filtro.ppTxStatus()?.name()}))
                    """)
    Page<PedidoProdutoDadosCompletosDto> findAllByUsuNrId(@Param("usuNrId") Long usuNrId, PedidoProdutoFiltroForm filtro, Pageable pageable);

    @Query(nativeQuery = true,
            value = """
                    select pp.* from financeiro.pp_pedido_produto pp
                    where
                        pp.pp_nr_id in (:ppNrIds) 
                    and (pp.pp_tx_status = 'CRIADO' or pp.pp_tx_status = 'EM_PRAPARACAO')
                     and pp.usu_nr_id =:usuNrId
                    """)
    List<PedidoProdutoEntidade> findAllPedidosStatusCriadoByUsuNrId(
            @Param("ppNrIds") List<Long> ppNrIds,
            @Param("usuNrId") Long usuNrId);
}
