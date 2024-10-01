package org.feiraconectada.feiraconectadaapi.service.financeiro.produto.dto;

public interface ProdutoDadosCompletosDto {
    Long getProNrId();
    String getProTxNome();
    Double getProNrPreco();
    Long getProNrQuantidade();
    String getEstTxNome();
    Long getEstNrId();
    String getIpTxImagem();
}
