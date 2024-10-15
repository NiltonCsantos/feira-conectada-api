package org.feiraconectada.feiraconectadaapi.service.autenticacao.usuario.dto;

import jakarta.validation.constraints.NotNull;

public record ExpoTokenForm(
        @NotNull
        String token
) {
}
