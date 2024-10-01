package org.feiraconectada.feiraconectadaapi.service.autenticacao.usuario.dto;

import org.feiraconectada.feiraconectadaapi.model.autenticacao.UsuarioEntidade;

public record UsuarioDto(
        Long usuNrId,
        String usuTxNome,
        String usuTxEmail
) {
    public UsuarioDto(UsuarioEntidade entidade) {
        this(entidade.getId(),
                entidade.getUsuTxNome(),
                entidade.getUsuTxEmail());
    }
}
