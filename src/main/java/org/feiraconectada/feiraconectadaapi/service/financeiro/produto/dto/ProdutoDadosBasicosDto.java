package org.feiraconectada.feiraconectadaapi.service.financeiro.produto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public interface ProdutoDadosBasicosDto {
    Long getProNrId();
    String getProTxNome();
    Double getProNrPreco();
    Long getProNrQuantidade();
}
