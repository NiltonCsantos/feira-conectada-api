package org.feiraconectada.feiraconectadaapi.controller;

import org.feiraconectada.feiraconectadaapi.dto.response.AuthorizationResponse;
import org.feiraconectada.feiraconectadaapi.service.AuthorizationService;
import org.feiraconectada.feiraconectadaapi.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorizationController {


    private final TokenService tokenService;

    private final AuthorizationService authorizationService;

    @Autowired
    public AuthorizationController(TokenService tokenService, AuthorizationService authorizationService) {

        this.tokenService=tokenService;

        this.authorizationService=authorizationService;

    }


    @PostMapping("/refrash-token")
    public ResponseEntity<AuthorizationResponse> refreshToken(@RequestHeader("Authorization") String a){

        System.out.println(a);

        return null;

    }

}
