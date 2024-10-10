package org.feiraconectada.feiraconectadaapi.service.financeiro.produto.dto;

public interface ProdutoDadosCompletosDto {
    Long getProNrId();
    String getProTxNome();
    Double getProNrPreco();
    Long getProNrQuantidade();
    String getEstTxNome();
    Long getEstNrId();
    String getIpTxImagem();
    Long getVenNrId();
    String getUsuTxNome();
    String getVenTxNumeroLoja();
    String getIvTxImagem();
}
