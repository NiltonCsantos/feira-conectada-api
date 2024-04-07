package org.feiraconectada.feiraconectadaapi.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.bouncycastle.math.ec.rfc8032.Ed448;
import org.feiraconectada.feiraconectadaapi.dto.request.UserLogin;
import org.feiraconectada.feiraconectadaapi.exceptions.TokenException;
import org.feiraconectada.feiraconectadaapi.model.UserModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.Signature;
import java.util.Calendar;
import java.util.Date;

@Service
public class TokenService {

    @Value("${api.token.secret}")
    private String secret;

    public TokenService(){
        Calendar calendar= Calendar.getInstance();
    }

    public String generateToken(UserModel userModel){
        try {


            return   Jwts.builder()
                    .setIssuer("auth-feira-app")
                    .setSubject(userModel.getEmail())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis()+3000*1000))
                    .signWith(getSignKey(), SignatureAlgorithm.HS512).
                    compact();



        }catch (JwtException e){

            System.out.println(e.getMessage());

            return null;

        }
    }

    public  String generateRefrashToken(UserModel user){

        try{

            return  Jwts.builder()
                    .setIssuer("auth-feira-app")
                    .setSubject(user.getEmail())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis()+36000000))
                    .signWith(getSignKey(), SignatureAlgorithm.HS512).
                    compact();

        }catch (JwtException e){

            System.out.println(e.getMessage());

            return null;

        }

    }

    public String validateToken(String token){

        System.out.println("VALIDANDO ");

        try {

            return Jwts.parserBuilder()
                    .setSigningKey(getSignKey())
                    .build().
                    parseClaimsJws(token).
                    getBody().
                    getSubject();

        } catch (ExpiredJwtException e) {

            System.out.println("EXPIROU");

            System.out.println("LANÇANDO exceção");
            throw  new TokenException();


        }

    }

    public String validateRefrashToken(String refreshToken){
        try {

            return Jwts.parserBuilder()
                    .setSigningKey(getSignKey())
                    .build().
                    parseClaimsJws(refreshToken).
                    getBody().
                    getSubject();

        } catch (ExpiredJwtException e) {

            throw  new TokenException();


        }
    }

    private Key getSignKey(){
        return Keys.hmacShaKeyFor(secret.getBytes());
    }




}
