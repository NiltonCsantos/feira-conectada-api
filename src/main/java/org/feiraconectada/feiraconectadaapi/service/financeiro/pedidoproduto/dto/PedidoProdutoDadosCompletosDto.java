package org.feiraconectada.feiraconectadaapi.service.financeiro.pedidoproduto.dto;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface PedidoProdutoDadosCompletosDto {
    Long getPpNrId();
    LocalDateTime getPpDtCriado();
    String getPpTxStatus();
    BigDecimal getPpNrPreco();
    Integer getPpNrQuantidadeProduto();
    Long getProNrId();
    BigDecimal getProNrPreco();
    String getProTxNome();
    String getIpTxImagem();
    Long getVenNrId();
    String getVenTxNome();
    String getIvTxImagem();
}
