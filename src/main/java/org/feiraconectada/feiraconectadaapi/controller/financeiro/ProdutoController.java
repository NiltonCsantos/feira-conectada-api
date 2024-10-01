package org.feiraconectada.feiraconectadaapi.controller.financeiro;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.feiraconectada.feiraconectadaapi.service.financeiro.produto.ProdutoService;
import org.feiraconectada.feiraconectadaapi.service.financeiro.produto.dto.ProdutoDadosCompletosDto;
import org.feiraconectada.feiraconectadaapi.service.financeiro.produto.form.ProdutoFiltrosForm;
import org.feiraconectada.feiraconectadaapi.service.financeiro.produto.form.ProdutoForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("v1")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping("/produtos")
    @Operation(summary = "Cadastrar ou atualizar produto", description = "Endpoint responsável por cadastrar ou atualizar um produto.")
    @ApiResponse(responseCode = "201", description = "CREATED")
    public ResponseEntity<Void> cadastreAddress(@RequestBody @Valid ProdutoForm form){
        this.produtoService.cadatrarOuAtualizarProduto(form);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/produtos")
    @Operation(summary = "buscar produtos", description = "Endpoint responsável por buscar produtos")
    @ApiResponse(responseCode = "200", description = "Ok", content = @Content(array = @ArraySchema(schema = @Schema( implementation = ProdutoDadosCompletosDto.class))))
    public ResponseEntity<Page<ProdutoDadosCompletosDto>> listarProdutos( ProdutoFiltrosForm form, Pageable pageable){
        return ResponseEntity.ok( produtoService.listarProdutos(form, pageable));
    }

    @GetMapping("/produtos/{proNrId}")
    @Operation(summary = "buscar produto por id", description = "Endpoint responsável por buscar produto por id.")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ProdutoDadosCompletosDto.class)))
    public ResponseEntity<ProdutoDadosCompletosDto> buscarProdutosPorId(@PathVariable Long proNrId){
        return ResponseEntity.ok( produtoService.buscarProdutoPorId(proNrId));
    }

}
