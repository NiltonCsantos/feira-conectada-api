package org.feiraconectada.feiraconectadaapi.controller.financeiro;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.feiraconectada.feiraconectadaapi.service.financeiro.estoque.EstoqueService;
import org.feiraconectada.feiraconectadaapi.service.financeiro.estoque.dto.EstoqueDto;
import org.feiraconectada.feiraconectadaapi.service.financeiro.estoque.form.EstoqueForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1")
@RequiredArgsConstructor
public class EstoqueController {

    private final EstoqueService estoqueService;

    @PostMapping("/estoques")
    @Operation(summary = "Cadastrar ou atualizar Estoque", description = "Endpoint responsável por cadastrar ou atualizar um estoque.")
    @ApiResponse(responseCode = "201", description = "CREATED")
    public ResponseEntity<Void> cadastarOuAtualizarEstoque(@RequestBody @Valid EstoqueForm form){
        estoqueService.cadastrarOuAtualizarEstoque(form);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("/estoques/nichos/{nicNrId}")
    @Operation(summary = "Listagem de estoques", description = "Endpoint responsável por listar estoque com base no nicho.")
    @ApiResponse(responseCode = "200", description = "Ok", content = @Content(array = @ArraySchema(schema = @Schema( implementation = EstoqueDto.class))))
    public ResponseEntity<Page<EstoqueDto>> buscarEstoquePorNicho(@PathVariable Long nicNrId, Pageable pageable){
        return  ResponseEntity.ok(estoqueService.listarEstoquePorNicho(nicNrId, pageable));
    }

    @GetMapping("/estoques/{estNrId}")
    @Operation(summary = "buscar estoque por id do estoque", description = "Endpoint responsável por buscar estoque por id do estoque.")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EstoqueDto.class)))
    public ResponseEntity<EstoqueDto> buscarEstoquePorId(@PathVariable Long estNrId){
        return ResponseEntity.ok(estoqueService.buscarEstoquePorId(estNrId));
    }

}
