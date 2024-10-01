package org.feiraconectada.feiraconectadaapi.repository.financeiro;

import org.feiraconectada.feiraconectadaapi.model.financeiro.ImagemVendedorEntidade;
import org.feiraconectada.feiraconectadaapi.model.financeiro.VendedorEntidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagemVendedorRepository extends JpaRepository<ImagemVendedorEntidade, Long> {
}
