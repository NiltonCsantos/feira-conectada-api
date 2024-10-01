package org.feiraconectada.feiraconectadaapi.service.financeiro.vendedor;

import org.feiraconectada.feiraconectadaapi.service.financeiro.estoque.dto.EstoqueDto;
import org.feiraconectada.feiraconectadaapi.service.financeiro.produto.dto.ProdutoDadosCompletosDto;
import org.feiraconectada.feiraconectadaapi.service.financeiro.vendedor.dto.VendedorDadosBasicosDto;
import org.feiraconectada.feiraconectadaapi.service.financeiro.vendedor.dto.VendedorDadosCompletoDto;
import org.feiraconectada.feiraconectadaapi.service.financeiro.vendedor.form.VendedorFiltrosForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.Optional;

public interface VendedorService {
    Long contarVendas();
    Long contarProdutosCadastrados();
    Long contarStocksCadastrados();
    Page<ProdutoDadosCompletosDto> listarProdutos(Pageable pageable);
    Page<EstoqueDto> listarEstoques(Pageable pageable);
    Page<ProdutoDadosCompletosDto> listarProdutosVendidos(Pageable pageable);
    BigDecimal contabilizarValorInvestido();
    BigDecimal contabilizarLucro();

    void confirmarPedido(Long proNrId);
}
