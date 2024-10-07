package org.feiraconectada.feiraconectadaapi.service.financeiro.pedidoproduto.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record PedidoProdutoForm(
        @NotNull
        @JsonProperty(required = true)
        Long proNrId,
        @NotNull
        @Min(1)
        @JsonProperty(required = true)
        Integer proNrQuantidade
        )
{
}
