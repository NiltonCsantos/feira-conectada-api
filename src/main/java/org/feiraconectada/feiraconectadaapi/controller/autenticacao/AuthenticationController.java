package org.feiraconectada.feiraconectadaapi.controller.autenticacao;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.feiraconectada.feiraconectadaapi.service.autenticacao.authenticate.AuthenticationService;
import org.feiraconectada.feiraconectadaapi.service.autenticacao.authenticate.dto.AuthDto;
import org.feiraconectada.feiraconectadaapi.service.autenticacao.usuario.form.FeiranteRegistroForm;
import org.feiraconectada.feiraconectadaapi.service.autenticacao.usuario.form.UsuarioLoginForm;
import org.feiraconectada.feiraconectadaapi.service.autenticacao.usuario.form.UsuarioRegistroForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/registrar-usuarios")
    @Operation(summary = "Cadastro de usuarios", description = "Endpoint responsável por cadastrar um usuario.")
    @ApiResponse(responseCode = "201", description = "CREATED")
    public ResponseEntity<Void> registrarUsuario(@RequestBody @Valid UsuarioRegistroForm user){
        authenticationService.salvarUsuario(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/registrar-feirantes")
    @Operation(summary = "Cadastro de feirantes", description = "Endpoint responsável por cadastrar um feirante.")
    @ApiResponse(responseCode = "201", description = "CREATED")
    public ResponseEntity<Void> registrarFeirante(@RequestBody @Valid FeiranteRegistroForm admin){
        authenticationService.salvarFeirante(admin);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PatchMapping("/redefinir-senha")
    public ResponseEntity<Void> redefinirSenha(){
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    @Operation(summary = "Login de usuarios", description = "Endpoint responsável por permitir que um usuario faça login.")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<AuthDto> login(@RequestBody @Valid UsuarioLoginForm userLoginForm){
        return  ResponseEntity.ok(authenticationService.fazerLogin(userLoginForm));
    }

    @PostMapping("/login-com-token/{refreshToken}")
    @Operation(summary = "Login de usuarios com token", description = "Endpoint responsável por permitir que um usuario faça login com token.")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<AuthDto> loginComToken(@PathVariable String refreshToken){
        return ResponseEntity.ok( this.authenticationService.fazerLoginComToken(refreshToken));
    }
}
