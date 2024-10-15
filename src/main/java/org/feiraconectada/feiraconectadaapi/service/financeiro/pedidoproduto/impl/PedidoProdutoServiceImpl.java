package org.feiraconectada.feiraconectadaapi.service.financeiro.pedidoproduto.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.feiraconectada.feiraconectadaapi.exceptions.NotFoundException;
import org.feiraconectada.feiraconectadaapi.exceptions.QuantidadeDeProdutosInsuficenteException;
import org.feiraconectada.feiraconectadaapi.model.financeiro.PedidoProdutoEntidade;
import org.feiraconectada.feiraconectadaapi.model.financeiro.ProdutoEntidade;
import org.feiraconectada.feiraconectadaapi.model.financeiro.enuns.StatusPedidoEnum;
import org.feiraconectada.feiraconectadaapi.repository.financeiro.PedidoProdutoRepository;
import org.feiraconectada.feiraconectadaapi.repository.financeiro.ProdutoRepository;
import org.feiraconectada.feiraconectadaapi.service.base.impl.BaseServiceImpl;
import org.feiraconectada.feiraconectadaapi.service.expo.ExpoServiceImpl;
import org.feiraconectada.feiraconectadaapi.service.financeiro.pedidoproduto.PedidoProdutoService;
import org.feiraconectada.feiraconectadaapi.service.financeiro.pedidoproduto.dto.PedidoProdutoDadosCompletosDto;
import org.feiraconectada.feiraconectadaapi.service.financeiro.pedidoproduto.form.PedidoProdutoFiltroForm;
import org.feiraconectada.feiraconectadaapi.service.financeiro.pedidoproduto.form.PedidoProdutoForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PedidoProdutoServiceImpl extends BaseServiceImpl implements PedidoProdutoService{

    private final PedidoProdutoRepository pedidoProdutoRepository;
    private final ProdutoRepository produtoRepository;
    private final ExpoServiceImpl expoService;

    @Override
    @Transactional
    public void criarPedido(List<PedidoProdutoForm> pedidoProdutosForm) {

        var usuNrId = this.buscarUsuarioAutenticado().getUsuNrId();

        List<Long> proNrIds = new ArrayList<>();

        pedidoProdutosForm.forEach(pedidoProdutoForm -> proNrIds.add(pedidoProdutoForm.proNrId()));

        var existsProNrIds = produtoRepository.existsProdutos(proNrIds);

        if (!existsProNrIds){
            throw new NotFoundException("Um ou mais produtos selecionados não existem ou não estão disponíveis");
        }

        List<ProdutoEntidade> produtos = produtoRepository.findByProNrIdIn(proNrIds);

        List<PedidoProdutoEntidade> pedidosProdutos = new ArrayList<>();

        for (var pedido: pedidoProdutosForm){

            final var valorTotalPedido = calcularValorPedido(produtos, pedido);

            var pedidoProduto = PedidoProdutoEntidade
                    .builder()
                    .ppTxStatus(StatusPedidoEnum.CRIADO)
                    .ppDtCriado(LocalDateTime.now())
                    .proNrId(pedido.proNrId())
                    .usuNrId(usuNrId)
                    .ppNrPreco(valorTotalPedido)
                    .ppNrQuantidadeProduto(pedido.proNrQuantidade())
                    .build();

            pedidosProdutos.add(pedidoProduto);
        }

        pedidoProdutoRepository.saveAll(pedidosProdutos);

    }

    @Override
    public Page<PedidoProdutoDadosCompletosDto> listarPedidosDoUsuario(PedidoProdutoFiltroForm filtro, Pageable pageable) {
        Long usuNrId = this.buscarUsuarioAutenticado().getUsuNrId();
        return pedidoProdutoRepository.findAllByUsuNrId(usuNrId, filtro, pageable);
    }

    @Override
    @Transactional
    public List<Long>  verificarStatusDoPedido(List<Long> ppNrIds) {

        var usuario= buscarUsuarioAutenticado();

        var listaPedidosEmAndamento = pedidoProdutoRepository.findAllPedidosStatusCriadoByUsuNrId(ppNrIds, usuario.getUsuNrId());

        var listaPedidoProdutosParaCancelar = new ArrayList<PedidoProdutoEntidade>();
        var listaProdutosParaAtulizarQuantidade = new ArrayList<ProdutoEntidade>();

        var pedidosCriados = listaPedidosEmAndamento.stream()
                .filter(pedidoProdutoEntidade -> pedidoProdutoEntidade.getPpTxStatus() == StatusPedidoEnum.CRIADO)
                .toList();

        LocalDateTime momentoAtual = LocalDateTime.now();

        for (PedidoProdutoEntidade pedidoProduto:pedidosCriados){
            if (Duration.between(pedidoProduto.getPpDtCriado(), momentoAtual).toMinutes() >= 30) {

                var produto = produtoRepository
                        .findById(pedidoProduto.getProNrId())
                                .orElseThrow(() -> new NotFoundException("Produto não encontrado"));

                produto.setProNrQuantidade(produto.getProNrQuantidade() + pedidoProduto.getPpNrQuantidadeProduto());
                pedidoProduto.setPpTxStatus(StatusPedidoEnum.CANCELAD0);

                listaProdutosParaAtulizarQuantidade.add(produto);
                listaPedidoProdutosParaCancelar.add(pedidoProduto);
            }
        }

        pedidoProdutoRepository.saveAll(listaPedidoProdutosParaCancelar);
        produtoRepository.saveAll(listaProdutosParaAtulizarQuantidade);

        listaProdutosParaAtulizarQuantidade.forEach(produtoEntidade -> {
            enviarNotificacoes(usuario.getUsuTxExpoToken(), produtoEntidade.getProTxNome() + "Cancelado :(",
                    "Sentimos muito pelo incoveniente. Tente realizar o pedido com outro vendedor. Abraços"
       );
        });

        return listaPedidoProdutosParaCancelar.stream()
                .map(PedidoProdutoEntidade::getPpNrId)
                .toList();
    }

    @Override
    public void atualizarStatusDoPedido(StatusPedidoEnum statusPedidoEnum, Long ppNrId) {

            var pedidoProduto = pedidoProdutoRepository
                    .findById(ppNrId)
                    .orElseThrow(() -> new NotFoundException("Pedido não encontrado"));

            pedidoProduto.setPpTxStatus(statusPedidoEnum);
            pedidoProdutoRepository.save(pedidoProduto);
    }

    private BigDecimal calcularValorPedido(List<ProdutoEntidade> produtos, PedidoProdutoForm form) {

        BigDecimal valor = BigDecimal.ZERO;

        for (ProdutoEntidade produto : produtos) {

            if (produto.getProNrQuantidade() == 0) {
                throw new QuantidadeDeProdutosInsuficenteException(produto.getProTxNome());
            }

            if (produto.getProNrId().equals(form.proNrId())) {

                if (produto.getProNrQuantidade() < form.proNrQuantidade()) {
                    throw new QuantidadeDeProdutosInsuficenteException(produto.getProTxNome());
                }

                BigDecimal precoProduto = produto.getProNrPreco();
                BigDecimal quantidadeDesejada = BigDecimal.valueOf(form.proNrQuantidade());
                valor = valor.add(precoProduto.multiply(quantidadeDesejada));
                produto.setProNrQuantidade(produto.getProNrQuantidade()-form.proNrQuantidade());

                if (produto.getProNrQuantidade()==0){
                    produto.setProBlAtivo(false);
                }

                produtoRepository.save(produto);
                break;
            }
        }

        return valor;
    }

    @Async
    protected void enviarNotificacoes(String expoToken, String titulo, String mensagem){
        expoService.sendPushNotification(expoToken,mensagem, titulo );
    }

}
