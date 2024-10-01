package org.feiraconectada.feiraconectadaapi.controller.usuario;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.feiraconectada.feiraconectadaapi.service.autenticacao.usuario.dto.UsuarioDto;
import org.feiraconectada.feiraconectadaapi.service.autenticacao.usuario.form.UsuarioEdicaoForm;
import org.feiraconectada.feiraconectadaapi.service.endereco.dto.EnderecoDto;
import org.feiraconectada.feiraconectadaapi.service.autenticacao.usuario.UsuarioService;
import org.feiraconectada.feiraconectadaapi.service.financeiro.vendedor.dto.VendedorDadosBasicosDto;
import org.feiraconectada.feiraconectadaapi.service.financeiro.vendedor.dto.VendedorDadosCompletoDto;
import org.feiraconectada.feiraconectadaapi.service.financeiro.vendedor.form.VendedorFiltrosForm;
import org.feiraconectada.feiraconectadaapi.service.firebase.FireBaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final FireBaseService fireBaseService;
    @GetMapping("/usuarios/enderecos")
    @Operation(summary = "Listagem de enderecos do usuário autenticado", description = "Endpoint responsável por listar enderecos do usuário autenticado.")
    @ApiResponse(responseCode = "200", description = "Ok", content = @Content(array = @ArraySchema(schema = @Schema( implementation = EnderecoDto.class))))
    public ResponseEntity<Page<EnderecoDto>> listarEnderecosDoUsuario(@PageableDefault(size = Integer.MAX_VALUE) Pageable pageable){
        return  ResponseEntity.ok(usuarioService.buscarEnderecosDoUsuario(pageable));
    }

    @PostMapping("/usuarios-associar-enderecos/{endNrId}")
    @Operation(summary = "Associar endereço a um usuario", description = "Endpoint responsável por Associar endereço a um usuario.")
    @ApiResponse(responseCode = "201", description = "CREATED")
    public ResponseEntity<Void> associarEnderecoUsuariuo(@PathVariable("endNrId") Long endNrId){
        usuarioService.adicionarEnderecoAoUsuario(endNrId);
        return  ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/usuarios/feirantes")
    @Operation(summary = "Listagem de feirantes", description = "Endpoint responsável por listar feirantes.")
    @ApiResponse(responseCode = "200", description = "Ok", content = @Content(array = @ArraySchema(schema = @Schema( implementation = VendedorDadosBasicosDto.class))))
    public ResponseEntity<Page<VendedorDadosBasicosDto>> listarFeirantes(@PageableDefault(size = 20) Pageable pageable, VendedorFiltrosForm filtrosForm){
        return  ResponseEntity.ok(usuarioService.listarFeirantesComImagem(filtrosForm, pageable));
    }

    @PutMapping("usuarios")
    @Operation(summary = "Editar dados de um usuário", description = "Endpoint responsável por Editar dados de um usuário.")
    @ApiResponse(responseCode = "204", description = "NO CONTENT")
    public ResponseEntity<UsuarioDto> editarDadosUsuario(@RequestBody @Valid UsuarioEdicaoForm usuarioEdicaoForm){
        var usuarioDto = usuarioService.editarDadosUsuario(usuarioEdicaoForm);
        return  ResponseEntity.status(HttpStatus.OK).body(usuarioDto);
    }

    @PutMapping("teste")
    @Operation(summary = "Editar dados de um usuário", description = "Endpoint responsável por Editar dados de um usuário.")
    @ApiResponse(responseCode = "204", description = "NO CONTENT")
    public ResponseEntity<Void> tetse(){
        fireBaseService.sendNotification("", "AAAAA", "BBBB");
        return  ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("usuarios/adquirir-produtos")
    @Operation(summary = "Realizar uma compra", description = "Endpoint responsável por permitir que o usuário realize compras")
    @ApiResponse(responseCode = "204", description = "NO CONTENT")
    public ResponseEntity<Void> realizarCompra(){
        return  ResponseEntity.status(HttpStatus.OK).build();
    }

}
