package org.feiraconectada.feiraconectadaapi.service.financeiro.nicho.dto;

import org.feiraconectada.feiraconectadaapi.model.financeiro.NichoEntidade;
import org.feiraconectada.feiraconectadaapi.model.financeiro.enuns.NichoRoleEnum;

public record NichoDto(
        Long nicNrId,
        NichoRoleEnum nicTxNome
) {
    public NichoDto(NichoEntidade entidade) {
        this(entidade.getNicNrId(),
                entidade.getNicTxNome());
    }
}
