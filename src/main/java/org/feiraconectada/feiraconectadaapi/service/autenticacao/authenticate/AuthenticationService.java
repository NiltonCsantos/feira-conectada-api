package org.feiraconectada.feiraconectadaapi.service.autenticacao.authenticate;

import org.feiraconectada.feiraconectadaapi.service.autenticacao.authenticate.dto.AuthDto;
import org.feiraconectada.feiraconectadaapi.service.autenticacao.usuario.form.FeiranteRegistroForm;
import org.feiraconectada.feiraconectadaapi.service.autenticacao.usuario.form.UsuarioRegistroForm;
import org.feiraconectada.feiraconectadaapi.service.autenticacao.usuario.form.UsuarioLoginForm;

public interface AuthenticationService {
    void salvarUsuario(UsuarioRegistroForm user);
    void salvarFeirante(FeiranteRegistroForm user);
    AuthDto fazerLogin(UsuarioLoginForm user);
    AuthDto fazerLoginComToken(String refreshToken);
}
