package org.feiraconectada.feiraconectadaapi.controller;

import jakarta.validation.Valid;
import org.feiraconectada.feiraconectadaapi.dto.request.RefrashTokenRequest;
import org.feiraconectada.feiraconectadaapi.dto.request.UserAdmin;
import org.feiraconectada.feiraconectadaapi.dto.request.UserLogin;
import org.feiraconectada.feiraconectadaapi.dto.request.UserRegister;
import org.feiraconectada.feiraconectadaapi.dto.response.AuthResponse;
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
    public ResponseEntity<Void> cadastre(@RequestBody @Valid UserRegister user){

        authenticateService.save(user);

        return ResponseEntity.ok().build();

    }

    @PostMapping("/register/admin")
    public ResponseEntity<Void> cadastreAdmin(@RequestBody @Valid UserAdmin admin){

        authenticateService.saveAdmin(admin);
        return ResponseEntity.ok().build();

    }



    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid UserLogin userLogin){

        return  ResponseEntity.ok(authenticateService.connnect(userLogin));

    }

    @PostMapping("/login/refrash")
    public ResponseEntity<AuthResponse> loginOnToken(@RequestBody @Valid RefrashTokenRequest request){


        return ResponseEntity.ok( this.authenticateService.loginOnToken(request.refrashToken()) );


    }




}
