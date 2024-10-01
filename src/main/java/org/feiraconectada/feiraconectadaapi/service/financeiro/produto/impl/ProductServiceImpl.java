package org.feiraconectada.feiraconectadaapi.service.financeiro.produto.impl;

import lombok.RequiredArgsConstructor;
import org.feiraconectada.feiraconectadaapi.exceptions.NotFoundException;
import org.feiraconectada.feiraconectadaapi.model.financeiro.ProdutoEntidade;
import org.feiraconectada.feiraconectadaapi.repository.financeiro.EstoqueRepository;
import org.feiraconectada.feiraconectadaapi.repository.financeiro.ProdutoRepository;
import org.feiraconectada.feiraconectadaapi.service.financeiro.produto.ProdutoService;
import org.feiraconectada.feiraconectadaapi.service.financeiro.produto.dto.ProdutoDadosBasicosDto;
import org.feiraconectada.feiraconectadaapi.service.financeiro.produto.dto.ProdutoDadosCompletosDto;
import org.feiraconectada.feiraconectadaapi.service.financeiro.produto.form.ProdutoFiltrosForm;
import org.feiraconectada.feiraconectadaapi.service.financeiro.produto.form.ProdutoForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProdutoService {

    private final ProdutoRepository produtoRepository;

    private  final EstoqueRepository estoqueRepository;

    @Override
    public void cadatrarOuAtualizarProduto(ProdutoForm form){

        estoqueRepository.findById(form.estNrId())
                .orElseThrow(() -> new NotFoundException("Estoque não encontrado"));

        final var produto= form.proNrId()!=null?
                produtoRepository.findById(form.proNrId())
                        .orElseThrow(() -> new NotFoundException("Produto não encontrado")):
                new ProdutoEntidade();

        produto.setEstNrId(form.estNrId());
        produto.setProTxNome(form.proTxNome());
        produto.setProNrQuantidade(form.proNrQuantidade());
        produto.setProNrPreco(form.proNrPreco());
        produto.setProBlAtivo(form.proBlAtivo());

        this.produtoRepository.save(produto);

    }

    @Override
    public Page<ProdutoDadosCompletosDto> listarProdutos(ProdutoFiltrosForm filtros, Pageable pageable){
        return produtoRepository.listarProdutoDadosCompletos(filtros,pageable);
    }

    @Override
    public ProdutoDadosCompletosDto buscarProdutoPorId(Long proNrId){
        var produto = this.produtoRepository.buscarProdutoDadosCompletos(proNrId)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado"));

        return produto;
    }

}
