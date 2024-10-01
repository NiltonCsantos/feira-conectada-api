package org.feiraconectada.feiraconectadaapi.service.financeiro.estoque.dto;

import org.feiraconectada.feiraconectadaapi.model.financeiro.EstoqueEntidade;

public record EstoqueDto(
        Long estNrId,
        String estTxNome
){
    public EstoqueDto(EstoqueEntidade entidade) {
        this(entidade.getEstNrId(), entidade.getEstTxNome());
    }
}
