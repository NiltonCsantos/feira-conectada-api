package org.feiraconectada.feiraconectadaapi.repository.autenticacao;

import org.feiraconectada.feiraconectadaapi.model.autenticacao.PerfilEntidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PerfilRepository extends JpaRepository<PerfilEntidade, Long> {

    @Query(nativeQuery = true,
            value = """
                    select per.* from autenticacao.per_perfil per
                    where per.per_tx_nome = 'USUARIO'
                    """)
    Optional<PerfilEntidade> findByPerfilUsuario();

    @Query(nativeQuery = true,
            value = """
                    select per.* from autenticacao.per_perfil per
                    where per.per_tx_nome = 'VENDEDOR'
                    """)
    Optional<PerfilEntidade> findByPerfilVendedor();

}
