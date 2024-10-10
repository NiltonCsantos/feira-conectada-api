package org.feiraconectada.feiraconectadaapi.service.financeiro.produto.form;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.feiraconectada.feiraconectadaapi.model.financeiro.enuns.NichoRoleEnum;

import java.math.BigDecimal;

public record ProdutoFiltrosForm(
        String proTxNome,
        BigDecimal proNrPreco,
        Long estNrId,
        Long nicNrId,
        Long venNrId,
        NichoRoleEnum nicTxNome
) {
}
