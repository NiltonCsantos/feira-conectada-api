package org.feiraconectada.feiraconectadaapi.service.financeiro.estoque.form;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.feiraconectada.feiraconectadaapi.model.financeiro.enuns.NichoRoleEnum;

public record EstoqueForm(
        Long estNrId,
        @NotBlank(message = "O campo nome não deve estar em branco")
        String estTxNome,
        @Enumerated(EnumType.STRING)
        @NotNull(message = "O campo nicho não deve estar em branco")
        NichoRoleEnum estTxNicho,
        @NotNull(message = "O campo id não deve estar em branco")
        Long venNrId,
        @NotNull(message = "O campo nicho é obrigatório")
        Long nicNrId
) {
}
