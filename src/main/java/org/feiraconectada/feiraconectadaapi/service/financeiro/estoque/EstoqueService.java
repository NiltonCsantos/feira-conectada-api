package org.feiraconectada.feiraconectadaapi.service.financeiro.estoque;

import org.feiraconectada.feiraconectadaapi.service.financeiro.estoque.dto.EstoqueDto;
import org.feiraconectada.feiraconectadaapi.service.financeiro.estoque.form.EstoqueForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EstoqueService {
     void cadastrarOuAtualizarEstoque( EstoqueForm form);
     EstoqueDto buscarEstoquePorId(Long estNrId);
     Page<EstoqueDto> listarEstoquePorNicho(Long nicNrId, Pageable pageable);
//     Page<EstoqueDto> buscarEstoqueDoVendedor(Long venNrId, Pageable pageable);
//     Page<ProductResponse> findProductsForStock(int id, Pageable pageable) throws NotFoundException;

}
