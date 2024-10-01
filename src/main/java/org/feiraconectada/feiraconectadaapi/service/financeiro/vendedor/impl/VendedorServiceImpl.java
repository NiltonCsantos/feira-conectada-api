package org.feiraconectada.feiraconectadaapi.service.financeiro.vendedor.impl;

import lombok.RequiredArgsConstructor;
import org.feiraconectada.feiraconectadaapi.repository.financeiro.VendedorRepository;
import org.feiraconectada.feiraconectadaapi.service.base.impl.BaseServiceImpl;
import org.feiraconectada.feiraconectadaapi.service.financeiro.estoque.dto.EstoqueDto;
import org.feiraconectada.feiraconectadaapi.service.financeiro.produto.dto.ProdutoDadosCompletosDto;
import org.feiraconectada.feiraconectadaapi.service.financeiro.vendedor.VendedorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class VendedorServiceImpl extends BaseServiceImpl implements VendedorService {

    private final VendedorRepository vendedorRepository;

    @Override
    public Long contarVendas() {
        var venNrId = this.buscarUsuarioAutenticado().getUsuNrId();
        return vendedorRepository.contarVendas(venNrId);
    }

    @Override
    public Long contarProdutosCadastrados() {
        var venNrId = this.buscarUsuarioAutenticado().getUsuNrId();
        return vendedorRepository.contarProdutosCadastrados(venNrId);
    }

    @Override
    public Long contarStocksCadastrados() {
        var venNrId = this.buscarUsuarioAutenticado().getUsuNrId();
        return vendedorRepository.contarEstoquesCadastrados(venNrId);
    }

    @Override
    public Page<ProdutoDadosCompletosDto> listarProdutos(Pageable pageable) {
        var venNrId = this.buscarUsuarioAutenticado().getUsuNrId();
        return vendedorRepository.listarProdutosDoVendedor(venNrId, pageable);
    }

    @Override
    public Page<EstoqueDto> listarEstoques(Pageable pageable) {
        return null;
    }

    @Override
    public Page<ProdutoDadosCompletosDto> listarProdutosVendidos(Pageable pageable) {
        return null;
    }

    @Override
    public BigDecimal contabilizarValorInvestido() {
        return null;
    }

    @Override
    public BigDecimal contabilizarLucro() {
        return null;
    }

    @Override
    public void confirmarPedido(Long proNrId) {

    }
}
