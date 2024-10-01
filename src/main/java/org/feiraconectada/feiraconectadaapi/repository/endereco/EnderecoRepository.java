package org.feiraconectada.feiraconectadaapi.repository.endereco;

import feign.Param;
import org.feiraconectada.feiraconectadaapi.model.endereco.EnderecoEntidade;
import org.feiraconectada.feiraconectadaapi.service.endereco.form.EnderecoFiltrosForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EnderecoRepository extends JpaRepository<EnderecoEntidade, Long> {

    @Query(nativeQuery = true,
            value = """
                    SELECT en.*
                    FROM endereco.end_endereco en
                    LEFT JOIN endereco.eu_end_usu eeu ON eeu.end_nr_id = en.end_nr_id AND eeu.usu_nr_id =:usuNrId
                      WHERE eeu.usu_nr_id IS NULL
                       and( 
                                (:#{#filtro.endTxCep()==null} or en.end_tx_cep like concat(coalesce(:#{#filtro.endTxCep()?.trim()}, ''), '%'))
                                or (:#{#filtro.endTxEstado()==null} or upper(en.end_tx_estado) like upper(concat(coalesce(:#{#filtro.endTxEstado()?.trim()}, ''), '%')))
                                or (:#{#filtro.endTxNome()==null} or upper(en.end_tx_nome) like upper(concat(coalesce(:#{#filtro.endTxNome()?.trim()}, ''), '%')))
                        )
                    """)
    Page<EnderecoEntidade> listarenderecos(EnderecoFiltrosForm filtro, @Param("usuNrId") Long usuNrId, Pageable pageable);

    @Query(nativeQuery = true,
            value = """
                    select en.* from endereco.end_endereco en
                    inner join endereco.eu_end_usu eeu on eeu.end_nr_id = en.end_nr_id
                    inner join autenticacao.usu_usuario usu on usu.usu_nr_id =:usuNrId
                    """)
    Page<EnderecoEntidade> listarEnderecosdoUsuario(@Param( "usuNrId") Long usuNrId, Pageable pageable);

}
