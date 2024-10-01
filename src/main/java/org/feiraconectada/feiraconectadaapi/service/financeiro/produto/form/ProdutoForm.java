package org.feiraconectada.feiraconectadaapi.service.financeiro.produto.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProdutoForm(

        Long proNrId,
        @NotBlank(message = "O campo nome não deve estar em branco")
        String proTxNome,
        @NotNull(message = "O campo preço não deve estar em branco")
        BigDecimal proNrPreco,
        @NotNull(message = "O campo quantidade não deve estar em branco")
        Long proNrQuantidade,
        @NotNull(message = "O campo estoque não deve estar em branco")
        Long estNrId,
        @NotNull(message = "O campo estoque não deve estar em branco")
        Boolean proBlAtivo,
        String ipTxImagem
) {
}
