package org.feiraconectada.feiraconectadaapi.service.autenticacao.authenticate.dto;

public record AuthorizationResponse(
        String acesstoken,

        String refrashToken

) {
}
