package org.feiraconectada.feiraconectadaapi.service.financeiro.pedidoproduto.dto;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.feiraconectada.feiraconectadaapi.service.financeiro.pedidoproduto.DadosSerializer;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@JsonPropertyOrder({ "venNrId", "usuTxNome", "venNrLoja", "ivTxImagem", "pedDtCriado", "pedido" })
public interface PedidoProdutoDadosCompletosDto {
    Long getVenNrId();
    String getUsuTxNome();
    Integer getVenNrLoja();
    String getIvTxImagem();
    Date getPedDtCriado();
    @JsonSerialize(using = DadosSerializer.class)
    String getPedido();
}
