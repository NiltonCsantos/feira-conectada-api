package org.feiraconectada.feiraconectadaapi.service.financeiro.pedidoproduto;

import org.feiraconectada.feiraconectadaapi.model.financeiro.enuns.StatusPedidoEnum;
import org.feiraconectada.feiraconectadaapi.service.financeiro.pedidoproduto.dto.PedidoProdutoDadosCompletosDto;
import org.feiraconectada.feiraconectadaapi.service.financeiro.pedidoproduto.form.PedidoProdutoFiltroForm;
import org.feiraconectada.feiraconectadaapi.service.financeiro.pedidoproduto.form.PedidoProdutoForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PedidoProdutoService {
    //cliente
    void criarPedido(List<PedidoProdutoForm> pedidoProdutosForm);
    //cliente
    Page<PedidoProdutoDadosCompletosDto> listarPedidosDoUsuario(PedidoProdutoFiltroForm filtro,Pageable pageable);
    //cliente
    List<Long>  verificarStatusDoPedido(List<Long> pedNrIds);
    //vendedor
    void atualizarStatusDoPedido(StatusPedidoEnum  statusPedidoEnum, Long pedNrId);
}
