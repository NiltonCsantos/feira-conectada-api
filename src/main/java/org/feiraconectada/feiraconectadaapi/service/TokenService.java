package org.feiraconectada.feiraconectadaapi.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.bouncycastle.math.ec.rfc8032.Ed448;
import org.feiraconectada.feiraconectadaapi.dto.request.UserLogin;
import org.feiraconectada.feiraconectadaapi.model.UserModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.Signature;
import java.util.Date;

@Service
public class TokenService {

    @Value("${api.token.secret}")
    private String secret;

    public String generateToken(UserModel userModel){
        try {


            String jws= Jwts.builder()
                    .setIssuer("auth-feira-app")
                    .setSubject(userModel.getEmail())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis()+3600000))
                    .signWith(getSignKey(), SignatureAlgorithm.HS512).
                    compact();

            return jws;

        }catch (JwtException e){

            System.out.println(e.getMessage());

            return null;

        }
    }

    public String validateToken(String token){

        try {

            System.out.println("VALIDANDO Token");

            return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody().getSubject();

            //OK, we can trust this JWT

        } catch (JwtException e) {

            System.out.println(e.getMessage());

            //don't trust the JWT!
        }



        return null;



    }

    private Key getSignKey(){
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

}
