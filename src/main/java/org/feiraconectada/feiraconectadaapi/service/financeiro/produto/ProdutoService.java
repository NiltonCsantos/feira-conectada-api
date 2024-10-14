package org.feiraconectada.feiraconectadaapi.service.financeiro.produto;

import org.feiraconectada.feiraconectadaapi.model.financeiro.ProdutoEntidade;
import org.feiraconectada.feiraconectadaapi.service.financeiro.produto.dto.ProdutoDadosBasicosDto;
import org.feiraconectada.feiraconectadaapi.service.financeiro.produto.dto.ProdutoDadosCompletosDto;
import org.feiraconectada.feiraconectadaapi.service.financeiro.produto.form.ProdutoFiltrosForm;
import org.feiraconectada.feiraconectadaapi.service.financeiro.produto.form.ProdutoForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProdutoService {
    void cadatrarOuAtualizarProduto(ProdutoForm produtoForm);
    Page<ProdutoDadosCompletosDto> listarProdutos( ProdutoFiltrosForm filtros, Pageable pageable);
    Page<ProdutoDadosCompletosDto> listarProdutosDoVendedorPorNicNrId(Long venNrId, Long nicNrId, Pageable pageable);
    ProdutoDadosCompletosDto buscarProdutoPorId(Long proNrId);
}
