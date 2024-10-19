package org.feiraconectada.feiraconectadaapi.controller.financeiro;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.feiraconectada.feiraconectadaapi.model.financeiro.enuns.StatusPedidoEnum;
import org.feiraconectada.feiraconectadaapi.service.financeiro.pedidoproduto.PedidoProdutoService;
import org.feiraconectada.feiraconectadaapi.service.financeiro.pedidoproduto.dto.PedidoProdutoDadosCompletosDto;
import org.feiraconectada.feiraconectadaapi.service.financeiro.pedidoproduto.form.PedidoProdutoFiltroForm;
import org.feiraconectada.feiraconectadaapi.service.financeiro.pedidoproduto.form.PedidoProdutoForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("v1")
@RequiredArgsConstructor
public class PedidoProdutoController {

    private final PedidoProdutoService pedidoProdutoService;

    //usuário
    @PostMapping("/pedidos-produtos")
    @Operation(summary = "Realizar pedido", description = "Endpoint responsável por permitir que um usuário realize um pedido.")
    @ApiResponse(responseCode = "201", description = "CREATED")
    public ResponseEntity<Void> cadastrarPedidos(@RequestBody List<@Valid PedidoProdutoForm> form){
        this.pedidoProdutoService.criarPedido(form);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //vendedor
    @PatchMapping("/pedidos-produtos/{pedNrId}")
    @Operation(summary = "Atualizar status do pedido", description = "Endpoint responsável por atualizar o status de um pedido.")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<Void> atualizarStatusDoPedido(@PathVariable Long pedNrId, @RequestParam StatusPedidoEnum ppTxStatus) {
        this.pedidoProdutoService.atualizarStatusDoPedido(ppTxStatus, pedNrId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    //cliente
    @GetMapping("/pedidos-produtos/verificar-pedidos")
    @Operation(summary = "Verificar status do pedido", description = "Endpoint responsável por verifcar o status de um pedido.")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<List<Long>> verificarStatusDoPedido( @RequestParam List<Long> pedNrIds) {
        var listaProdutosCancelados = pedidoProdutoService.verificarStatusDoPedido(pedNrIds);
        return ResponseEntity.status(HttpStatus.OK).body(listaProdutosCancelados);
    }
    //cliente
    @GetMapping("/pedidos-produtos")
    @Operation(summary = "Listar pedido", description = "Endpoint responsável por listar os pedidos de um usuário.")
    @ApiResponse(responseCode = "200", description = "OK")
    public ResponseEntity<Page<PedidoProdutoDadosCompletosDto>> listarPedido(PedidoProdutoFiltroForm filtro, @PageableDefault (size = Integer.MAX_VALUE) Pageable pageable) {
        var pedidoProdutoDadosCompletosDtos = this.pedidoProdutoService.listarPedidosDoUsuario(filtro, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(pedidoProdutoDadosCompletosDtos);
    }
}
