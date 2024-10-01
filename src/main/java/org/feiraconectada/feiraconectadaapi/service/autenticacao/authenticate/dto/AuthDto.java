package org.feiraconectada.feiraconectadaapi.service.autenticacao.authenticate.dto;

public record AuthDto(
        String acessToken,
        String refreshToken
) {
}
