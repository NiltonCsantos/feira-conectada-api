package org.feiraconectada.feiraconectadaapi.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthResponse(

        @JsonProperty("user")
        UserResponse userResponse,

        String acessToken,

        String refreshToken

) {
}
