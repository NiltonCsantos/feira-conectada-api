package org.feiraconectada.feiraconectadaapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserLogin(

        @NotBlank(message = "O campo email deve ser preenchido")
        String email,

        @NotBlank(message = "O campo senha deve ser preenchido")
        String password


) {

}
