package org.feiraconectada.feiraconectadaapi.service.endereco.form;

import jakarta.validation.constraints.NotNull;

public record EnderecoFiltrosForm(
        String endTxNome,
        String endTxCep,
        String endTxEstado
) {
}
