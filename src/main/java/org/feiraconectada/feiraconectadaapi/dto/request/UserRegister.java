package org.feiraconectada.feiraconectadaapi.dto.request;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.feiraconectada.feiraconectadaapi.enuns.UserRole;

public record UserRegister(

        @NotBlank(message = "O campo nome deve ser preenchido")
        String fullName,

        @NotBlank(message = "O campo email deve ser preenchido")
        @Email
        String email,

        @NotBlank(message = "O campo senha deve ser preenchido")
        String password,


        UserAddresRequest userAddresRequest

//        @Enumerated
//        UserRole role


){


}
