package org.feiraconectada.feiraconectadaapi.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.bouncycastle.math.ec.rfc8032.Ed448;
import org.feiraconectada.feiraconectadaapi.dto.request.UserLogin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class TokenService {

    @Value("${api.token.secret}")
    private String secret;

    public String generateToken(UserLogin userLogin){
        try {

            Key key= Keys.hmacShaKeyFor(secret.getBytes());

            String jws= Jwts.builder()
                    .issuer("auth-feira-app")
                    .subject(userLogin.email())
                    .issuedAt(new Date())
                    .expiration(new Date(System.currentTimeMillis()+3600000))
                    .signWith(key).
                    compact();

            return jws;

        }catch (JwtException e){

            System.out.println(e.getMessage());

            return null;

        }
    }

    public String validateToken(String token){

        Key key = Keys.hmacShaKeyFor(secret.getBytes());


        return null;



    }

}
