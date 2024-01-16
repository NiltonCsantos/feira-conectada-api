package org.feiraconectada.feiraconectadaapi.dto.request;

import org.feiraconectada.feiraconectadaapi.enuns.UserRole;

public record UserRegister(

        String fullName,

        String email,

        String password,

        UserRole role


){


}
