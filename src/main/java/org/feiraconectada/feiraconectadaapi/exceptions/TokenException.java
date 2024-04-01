package org.feiraconectada.feiraconectadaapi.exceptions;

import io.jsonwebtoken.JwtException;

public class TokenException extends RuntimeException {

    public TokenException() {
        super("Token Expirado");
    }
}
