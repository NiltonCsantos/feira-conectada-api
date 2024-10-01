package org.feiraconectada.feiraconectadaapi.controller.endereco;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.feiraconectada.feiraconectadaapi.service.endereco.EnderecoService;
import org.feiraconectada.feiraconectadaapi.service.endereco.dto.EnderecoDto;
import org.feiraconectada.feiraconectadaapi.service.endereco.form.EnderecoFiltrosForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1")
@RequiredArgsConstructor
public class EnderecoController {

    private final EnderecoService enderecoService;

    @GetMapping("/enderecos")
    @Operation(summary = "Listagem de enderecos", description = "Endpoint responsável por listar enderecos.")
    @ApiResponse(responseCode = "200", description = "Ok", content = @Content(array = @ArraySchema(schema = @Schema( implementation = EnderecoDto.class))))
    public ResponseEntity<Page<EnderecoDto>> listarEnderecos(@PageableDefault(size = 20) Pageable pageable, EnderecoFiltrosForm filtro){
        var listaEnderecos= enderecoService.listarEnderecos(filtro, pageable);
        return  ResponseEntity.ok(listaEnderecos);
    }

    @GetMapping("/enderecos/{endNrId}")
    @Operation(summary = "buscar endereco por id", description = "Endpoint responsável por buscar endereco por id.")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EnderecoDto.class)))
    public ResponseEntity<EnderecoDto> buscarEnderecoPorId(@PathVariable Long endNrId){
        var endereco = enderecoService.buscarEnderecoPorId(endNrId);
        return ResponseEntity.ok(endereco);
    }

}
