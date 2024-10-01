package org.feiraconectada.feiraconectadaapi.repository.financeiro;

import org.feiraconectada.feiraconectadaapi.model.financeiro.EstoqueEntidade;
import org.feiraconectada.feiraconectadaapi.model.financeiro.ProdutoEntidade;
import org.feiraconectada.feiraconectadaapi.model.financeiro.VendedorEntidade;
import org.feiraconectada.feiraconectadaapi.service.financeiro.produto.dto.ProdutoDadosCompletosDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VendedorRepository extends JpaRepository<VendedorEntidade, Long> {

    @Query(nativeQuery = true,
            value = """
                    SELECT est.* financeiro.est_estoque est
                    WHERE  est.ven_nr_id = :venNrId  
                    """)
    Page<EstoqueEntidade> listarEstoques(@Param("venNrId") Long venNrId, Pageable pageable);

    @Query(nativeQuery = true,
            value = """
                    select count(*)
                    from financeiro.pro_produto pro
                    inner join financeiro.pp_pedido_produto pp on pp.pro_nr_id = pro.pro_nr_id
                    inner join financeiro.est_estoque est on est.est_nr_id = pro.est_nr_id
                    where est.ven_nr_id =:venNrId
                    and pp.pp_tx_status = 'FINALIZADO'
                    """)
    Long contarVendas(@Param("venNrId") Long venNrId);

    @Query(nativeQuery = true,
            value = """
                    select count(pro.*)
                      from financeiro.pro_produto pro
                      inner join financeiro.est_estoque est on est.est_nr_id = pro.est_nr_id
                      where est.ven_nr_id =:venNrId
                    """)
    Long contarProdutosCadastrados(@Param("venNrId") Long venNrId);

    @Query(nativeQuery = true,
            value = """
                    select count(est.*)
                      from  financeiro.est_estoque est
                      where est.ven_nr_id =:venNrId
                    """)
    Long contarEstoquesCadastrados(@Param("venNrId") Long venNrId);

    @Query(nativeQuery = true,
            value = """
                    select
                          pro.pro_nr_id ProNrId,
                          pro.pro_tx_nome ProTxNome,
                          pro.pro_nr_preco ProNrPreco,
                          pro.pro_nr_quantidade ProNrQuantidade,
                          est.est_tx_nome estTxNome,
                          est.est_nr_id EstNrId,
                          ip.ip_tx_imagem IpTxImagem
                      from financeiro.pro_produto pro
                      inner join financeiro.est_estoque est on est.est_nr_id = pro.est_nr_id
                      left join financeiro.ip_imagem_produto ip on ip.ip_nr_id = pro.ip_nr_id
                      where est.ven_nr_id =:venNrId
                    """)
    Page<ProdutoDadosCompletosDto> listarProdutosDoVendedor(@Param("venNrId") Long venNrId, Pageable pageable);

}
