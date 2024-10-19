package org.feiraconectada.feiraconectadaapi.service.financeiro.pedidoproduto.form;

import org.feiraconectada.feiraconectadaapi.model.financeiro.enuns.StatusPedidoEnum;

public record PedidoProdutoFiltroForm(
        StatusPedidoEnum pedTxStatus
) {
}
