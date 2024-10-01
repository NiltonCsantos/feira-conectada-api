package org.feiraconectada.feiraconectadaapi.service.autenticacao.token;

import org.feiraconectada.feiraconectadaapi.model.autenticacao.UsuarioEntidade;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface TokenService {
     String gerarToken(UserDetails usuario, Map<String, Object> claims);
     String gerarRefreshToken(UserDetails usuario);
     String validarToken(String token);
     String gerarTokenTemporario(UserDetails usuario);
}
