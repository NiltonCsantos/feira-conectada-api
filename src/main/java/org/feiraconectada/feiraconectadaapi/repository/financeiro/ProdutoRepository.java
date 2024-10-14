package org.feiraconectada.feiraconectadaapi.repository.financeiro;

import org.feiraconectada.feiraconectadaapi.model.financeiro.ProdutoEntidade;
import org.feiraconectada.feiraconectadaapi.service.financeiro.produto.dto.ProdutoDadosBasicosDto;
import org.feiraconectada.feiraconectadaapi.service.financeiro.produto.dto.ProdutoDadosCompletosDto;
import org.feiraconectada.feiraconectadaapi.service.financeiro.produto.form.ProdutoFiltrosForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntidade, Long> {

    @Query(nativeQuery = true,
            value = """
                     select 
                         pro.pro_nr_id ProNrId,
                         pro.pro_tx_nome ProTxNome,
                         pro.pro_nr_preco ProNrPreco,
                         pro.pro_nr_quantidade ProNrQuantidade
                     from financeiro.pro_produto pro
                     """
    )
    Page<ProdutoDadosBasicosDto> listarProdutosDadosBasicos(Pageable pageable);

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
                     where pro.pro_nr_id =:proNrId
                     """)
    Optional<ProdutoDadosCompletosDto> buscarProdutoDadosCompletos(@Param("proNrId") Long proNrId);

    @Query(nativeQuery = true,
            value = """
                       select
                        pro.pro_nr_id ProNrId,
                        pro.pro_tx_nome ProTxNome,
                        pro.pro_nr_preco ProNrPreco,
                        pro.pro_nr_quantidade ProNrQuantidade,
                        est.est_tx_nome estTxNome,
                        est.est_nr_id EstNrId,
                        ip.ip_tx_imagem IpTxImagem,
                        ven.ven_nr_id VenNrId,
                        usu.usu_tx_nome usuTxNome,
                        ven.ven_nr_loja venTxNumeroLoja,
                        iv.iv_tx_imagem ivTxImagem
                    from financeiro.pro_produto pro
                        inner join financeiro.est_estoque est on pro.est_nr_id = est.est_nr_id
                        inner join financeiro.nic_nicho nic on nic.nic_nr_id = est.nic_nr_id
                        left join financeiro.ip_imagem_produto ip on ip.ip_nr_id = pro.ip_nr_id
                        inner join financeiro.ven_vendedor ven on ven.ven_nr_id = est.ven_nr_id
                        inner join autenticacao.usu_usuario usu on usu.usu_nr_id = ven.ven_nr_id
                        left join financeiro.iv_imagem_vendedor iv on iv.iv_nr_id = ven.iv_nr_id
                            where (pro.pro_bl_ativo = true)
                                and (:#{#filtro.venNrId()==null} or est.ven_nr_id =:#{#filtro.venNrId()})
                                and  (:#{#filtro.estNrId()==null} or est.est_nr_id =:#{#filtro.estNrId()})
                                and  (:#{#filtro.nicNrId()==null} or nic.nic_nr_id =:#{#filtro.nicNrId()})
                                and  (:#{#filtro.proNrPreco()==null} or pro.pro_nr_preco =:#{#filtro.proNrPreco()})
                                and  (:#{#filtro.proTxNome()==null} or upper(pro.pro_tx_nome) like upper(concat(coalesce(:#{#filtro.proTxNome()?.trim()}, ''), '%')))
                                and  (:#{#filtro.nicTxNome()==null} or upper(nic.nic_tx_nome) = upper(:#{#filtro.nicTxNome()?.name()?.trim()}))
                     """)
    Page<ProdutoDadosCompletosDto> listarProdutoDadosCompletos(ProdutoFiltrosForm filtro,  Pageable pageable);

    @Query(nativeQuery = true,
            value = """
                     select count(1) = :#{#proNrIds.size()}
                                   from financeiro.pro_produto pro
                                   where pro.pro_nr_id  in (:#{#proNrIds});
                    """)
    boolean existsProdutos(@Param("proNrIds")List<Long> proNrIds);

    List<ProdutoEntidade> findByProNrIdIn(@Param("proNrIds") List<Long> proNrIds);

    @Query(nativeQuery = true,
           value = """
                        select
                        pro.pro_nr_id ProNrId,
                        pro.pro_tx_nome ProTxNome,
                        pro.pro_nr_preco ProNrPreco,
                        pro.pro_nr_quantidade ProNrQuantidade,
                        est.est_tx_nome estTxNome,
                        est.est_nr_id EstNrId,
                        ip.ip_tx_imagem IpTxImagem,
                        ven.ven_nr_id VenNrId,
                        usu.usu_tx_nome usuTxNome,
                        ven.ven_nr_loja venTxNumeroLoja,
                        iv.iv_tx_imagem ivTxImagem
                    from financeiro.pro_produto pro
                        inner join financeiro.est_estoque est on pro.est_nr_id = est.est_nr_id
                        inner join financeiro.nic_nicho nic on nic.nic_nr_id = est.nic_nr_id
                        left join financeiro.ip_imagem_produto ip on ip.ip_nr_id = pro.ip_nr_id
                        inner join financeiro.ven_vendedor ven on ven.ven_nr_id = est.ven_nr_id
                        inner join autenticacao.usu_usuario usu on usu.usu_nr_id = ven.ven_nr_id
                        left join financeiro.iv_imagem_vendedor iv on iv.iv_nr_id = ven.iv_nr_id
                        where est.ven_nr_id=:venNrId and est.nic_nr_id=:nicNrId
            """
    )
    Page<ProdutoDadosCompletosDto> findAllByVenNrIdAndNicNrId(@Param("venNrId") Long venNrId, @Param("nicNrId") Long nicNrId, Pageable pageable);
}
