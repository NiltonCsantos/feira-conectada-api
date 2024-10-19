package org.feiraconectada.feiraconectadaapi.service.autenticacao.token.impl;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.feiraconectada.feiraconectadaapi.model.autenticacao.UsuarioEntidade;
import org.feiraconectada.feiraconectadaapi.service.autenticacao.authenticate.dto.AuthDto;
import org.feiraconectada.feiraconectadaapi.service.autenticacao.token.TokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${api.token.secret}")
    private String secret; // A chave secreta deve ser de pelo menos 64 caracteres

    // Construtor
    public TokenServiceImpl() {
        Calendar calendar = Calendar.getInstance();
    }

    @Override
    public String gerarToken(UserDetails usuario, Map<String, Object> claims) {
        return Jwts.builder()
                .setIssuer("auth-feira-app")
                .setSubject(usuario.getUsername())
                .addClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1500000000)) // 10 horas
                .signWith(getSignKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    @Override
    public String gerarTokenTemporario(UserDetails usuario) {
        return Jwts.builder()
                .setIssuer("auth-feira-app")
                .setSubject(usuario.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hora
                .signWith(getSignKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    @Override
    public String gerarRefreshToken(UserDetails usuario) {
        return Jwts.builder()
                .setIssuer("auth-feira-app")
                .setSubject(usuario.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 5184000000L)) // 60 dias
                .signWith(getSignKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    @Override
    public String validarToken(String token) {
            return Jwts.parserBuilder()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
    }
    private Key getSignKey(){
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
}
