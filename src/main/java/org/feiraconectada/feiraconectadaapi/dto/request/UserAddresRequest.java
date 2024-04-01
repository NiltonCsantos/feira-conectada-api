package org.feiraconectada.feiraconectadaapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserAddresRequest(
        @NotNull
        Integer idUser,
        @NotNull
        Integer idAddress
) {
}
