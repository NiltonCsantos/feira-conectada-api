package org.feiraconectada.feiraconectadaapi.repository.autenticacao;

import feign.Param;
import org.feiraconectada.feiraconectadaapi.model.autenticacao.CustomUserDetails;
import org.feiraconectada.feiraconectadaapi.model.autenticacao.UsuarioEntidade;
import org.feiraconectada.feiraconectadaapi.model.endereco.EnderecoEntidade;
import org.feiraconectada.feiraconectadaapi.service.financeiro.vendedor.dto.VendedorDadosBasicosDto;
import org.feiraconectada.feiraconectadaapi.service.financeiro.vendedor.form.VendedorFiltrosForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntidade, Long> {
    CustomUserDetails findByUsuTxEmail(String email);

    @Query(
            nativeQuery = true,
            value = """
                    select ven.ven_nr_id VenNrId, 
                    ven.ven_nr_loja VenTxNumeroLoja,
                     usu.usu_tx_nome UsuTxNome,
                     iv.iv_tx_imagem IvTxImagem,
                     nic.nic_tx_nome NicTxNome
                      from financeiro.ven_vendedor ven
                    inner join autenticacao.usu_usuario usu on usu.usu_nr_id = ven.ven_nr_id
                    inner join financeiro.nic_nicho nic on nic.nic_nr_id = ven.nic_nr_id
                    left join financeiro.iv_imagem_vendedor iv on ven.iv_nr_id = iv.iv_nr_id
                    where (:#{#filtro.usuTxNome()==null} or unaccent(upper(usu.usu_tx_nome)) like unaccent(upper(concat(coalesce(:#{#filtro.usuTxNome()?.trim()}, ''), '%'))))
                    """
    )
    Page<VendedorDadosBasicosDto>listarVendedoresComImagem(VendedorFiltrosForm filtro, Pageable pageable);

}



