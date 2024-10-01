package org.feiraconectada.feiraconectadaapi.service.autenticacao.usuario.form;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UsuarioLoginForm(
        @NotBlank(message = "O campo email deve ser preenchido")
        String usuTxEmail,
        @NotBlank(message = "O campo senha deve ser preenchido")
        @Length(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
        String usuTxSenha
) {

}
