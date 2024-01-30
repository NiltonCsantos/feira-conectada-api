package org.feiraconectada.feiraconectadaapi.dto.request;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.feiraconectada.feiraconectadaapi.enuns.UserRole;

public record UserRegister(

        @NotBlank(message = "O campo nome deve ser preenchido")
        String fullName,

        @NotBlank(message = "O campo email deve ser preenchido")
        String email,

        @NotBlank(message = "O campo senha deve ser preenchido")
        String password,

        @Enumerated
        UserRole role


){


}
