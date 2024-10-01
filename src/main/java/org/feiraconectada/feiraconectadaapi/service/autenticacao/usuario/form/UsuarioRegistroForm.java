package org.feiraconectada.feiraconectadaapi.service.autenticacao.usuario.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UsuarioRegistroForm(
        @NotBlank(message = "O campo nome deve ser preenchido")
        String usuTxNome,
        @NotBlank(message = "O campo email deve ser preenchido")
        @Email(message = "email inválido")
        String usuTxEmail,
        @NotBlank(message = "O campo senha deve ser preenchido")
        @Length(min = 8, message = "a senha deve ter no mínimo 8 caracteres")
        String usuTxSenha
) {
}
