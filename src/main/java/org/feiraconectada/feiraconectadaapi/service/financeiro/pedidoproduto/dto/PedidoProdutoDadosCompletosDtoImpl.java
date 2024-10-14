package org.feiraconectada.feiraconectadaapi.service.financeiro.pedidoproduto.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.feiraconectada.feiraconectadaapi.model.financeiro.enuns.StatusPedidoEnum;
import org.feiraconectada.feiraconectadaapi.service.financeiro.pedidoproduto.DadosSerializer;
import org.feiraconectada.feiraconectadaapi.service.financeiro.pedidoproduto.ListaDadosSerializer;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@JsonSerialize(using = ListaDadosSerializer.class)
public class PedidoProdutoDadosCompletosDtoImpl{
    private Long ppNrId; // ID do Pedido Produto
    private LocalDateTime ppDtCriado; // Data de criação do Pedido Produto
    private String ppTxStatus; // Status do Pedido Produto
    private BigDecimal ppNrPreco; // Preço do Pedido Produto
    private Integer ppNrQuantidadeProduto; // Quantidade de Produto no Pedido
    private Long proNrId; // ID do Produto
    private BigDecimal proNrPreco;
    private String proTxNome;
    private String ipTxImagem;
    private Long venNrId;
    private String venTxNome;
    private String ivTxImagem;

    public PedidoProdutoDadosCompletosDtoImpl(PedidoProdutoDadosCompletosDto pedidoProdutoDadosCompletosDto) {
        this.ppNrId = pedidoProdutoDadosCompletosDto.getPpNrId();
        this.ppDtCriado = pedidoProdutoDadosCompletosDto.getPpDtCriado();
        this.ppTxStatus = pedidoProdutoDadosCompletosDto.getPpTxStatus();
        this.ppNrPreco = pedidoProdutoDadosCompletosDto.getPpNrPreco();
        this.ppNrQuantidadeProduto = pedidoProdutoDadosCompletosDto.getPpNrQuantidadeProduto();
        this.proNrId = pedidoProdutoDadosCompletosDto.getProNrId();
        this.proNrPreco = pedidoProdutoDadosCompletosDto.getProNrPreco();
        this.proTxNome = pedidoProdutoDadosCompletosDto.getProTxNome();
        this.ipTxImagem = pedidoProdutoDadosCompletosDto.getIpTxImagem();
        this.venNrId = pedidoProdutoDadosCompletosDto.getVenNrId();
        this.venTxNome = pedidoProdutoDadosCompletosDto.getVenTxNome();
        this.ivTxImagem = pedidoProdutoDadosCompletosDto.getIvTxImagem();
    }
}
