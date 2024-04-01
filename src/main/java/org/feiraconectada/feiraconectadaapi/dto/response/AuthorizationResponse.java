package org.feiraconectada.feiraconectadaapi.dto.response;

public record AuthorizationResponse(
        String acesstoken,

        String refrashToken

) {
}
