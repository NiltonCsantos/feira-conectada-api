package org.feiraconectada.feiraconectadaapi.controller.financeiro;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.feiraconectada.feiraconectadaapi.service.financeiro.nicho.NichoService;
import org.feiraconectada.feiraconectadaapi.service.financeiro.nicho.dto.NichoDto;
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
public class NichoController {

    private final NichoService nichoService;
    @GetMapping("nichos/vendedores/{venNrId}")
    @Operation(summary = "Listagem de nichos", description = "Endpoint responsável por listar nichos dos Estoques com base no id do vendedor.")
    @ApiResponse(responseCode = "200", description = "Ok", content = @Content(array = @ArraySchema(schema = @Schema( implementation = NichoDto.class))))
    public ResponseEntity<Page<NichoDto>> buscarNichoDosEsToquesPorIdVendedor(@PathVariable Long venNrId, @PageableDefault (size = Integer.MAX_VALUE) Pageable pageable){
        var nichos = nichoService.listarNichoDosEstoquesPorIdVendedor(venNrId, pageable);
        return  ResponseEntity.ok(nichos);
    }

}
