package org.feiraconectada.feiraconectadaapi.service.financeiro.vendedor.form;

import jakarta.validation.constraints.NotNull;

public record VendedorFiltrosForm(
        String usuTxNome,
        @NotNull
        Long endNrId
) {
}
