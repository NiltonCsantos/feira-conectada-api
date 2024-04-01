package org.feiraconectada.feiraconectadaapi.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RefrashTokenRequest(

        @NotBlank
        String refrashToken
) {
}
