package org.feiraconectada.feiraconectadaapi.controller;

import org.feiraconectada.feiraconectadaapi.dto.request.UserLogin;
import org.feiraconectada.feiraconectadaapi.dto.request.UserRegister;
import org.feiraconectada.feiraconectadaapi.service.AuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private final AuthenticateService authenticateService;

    @Autowired
    public AuthenticationController(AuthenticateService authenticateService){
        this.authenticateService=authenticateService;
    }

    @PostMapping("/register")
    public ResponseEntity cadastre(@RequestBody UserRegister user){

        return authenticateService.save(user);

    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserLogin userLogin){

        return  authenticateService.connnect(userLogin);

    }

}
