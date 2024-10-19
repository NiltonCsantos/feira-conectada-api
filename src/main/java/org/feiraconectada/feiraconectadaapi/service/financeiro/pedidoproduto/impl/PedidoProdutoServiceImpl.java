package org.feiraconectada.feiraconectadaapi.service.financeiro.pedidoproduto.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.feiraconectada.feiraconectadaapi.exceptions.NotFoundException;
import org.feiraconectada.feiraconectadaapi.exceptions.QuantidadeDeProdutosInsuficenteException;
import org.feiraconectada.feiraconectadaapi.model.financeiro.PedidoEntidade;
import org.feiraconectada.feiraconectadaapi.model.financeiro.PedidoProdutoEntidade;
import org.feiraconectada.feiraconectadaapi.model.financeiro.ProdutoEntidade;
import org.feiraconectada.feiraconectadaapi.model.financeiro.enuns.StatusPedidoEnum;
import org.feiraconectada.feiraconectadaapi.repository.financeiro.PedidoProdutoRepository;
import org.feiraconectada.feiraconectadaapi.repository.financeiro.PedidoRepository;
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
public class PedidoProdutoServiceImpl extends BaseServiceImpl implements PedidoProdutoService {

    private final PedidoProdutoRepository pedidoProdutoRepository;
    private final ProdutoRepository produtoRepository;
    private final ExpoServiceImpl expoService;
    private final PedidoRepository pedidoRepository;

    @Override
    @Transactional
    public void criarPedido(List<PedidoProdutoForm> pedidoProdutosForm) {

        var usuario = this.buscarUsuarioAutenticado();

        List<Long> proNrIds = new ArrayList<>();

        pedidoProdutosForm.forEach(pedidoProdutoForm -> proNrIds.add(pedidoProdutoForm.proNrId()));

//        var existsProNrIds = produtoRepository.existsProdutos(proNrIds);

        List<ProdutoEntidade> produtos = produtoRepository.findByProNrIdIn(proNrIds);

        if (produtos.size()<pedidoProdutosForm.size()) {
            throw new NotFoundException("Um ou mais produtos selecionados não existem ou não estão disponíveis");
        }

//        List<ProdutoEntidade> produtos = produtoRepository.findByProNrIdIn(proNrIds);

        var pedido = new PedidoEntidade();
        pedido.setPedDtCriado(LocalDateTime.now());
        pedido.setPedTxStatus(StatusPedidoEnum.CRIADO);
        pedido.setUsuNrId(usuario.getUsuNrId());

        List<PedidoProdutoEntidade> pedidosProdutos = new ArrayList<>();

        BigDecimal valorTotalPedido = BigDecimal.ZERO;

        for (var pedidoProdutoForm : pedidoProdutosForm) {

            var valorPedidoProduto = calcularValorPedidoProduto(produtos, pedidoProdutoForm);
            valorTotalPedido = valorTotalPedido.add(valorPedidoProduto);
            var pedidoProduto = PedidoProdutoEntidade
                    .builder()
                    .proNrId(pedidoProdutoForm.proNrId())
                    .ppNrPreco(valorPedidoProduto)
                    .ppNrQuantidadeProduto(pedidoProdutoForm.ppNrQuantidade())
                    .build();

            pedidosProdutos.add(pedidoProduto);
        }

        pedido.setPedNrValorTotal(valorTotalPedido);

        pedidoRepository.save(pedido);

        pedidosProdutos.forEach(pedidoProdutoEntidade -> {
            pedidoProdutoEntidade.setPedNrId(pedido.getPedNrId());
        });

        pedidoProdutoRepository.saveAll(pedidosProdutos);
        enviarNotificacoes(usuario.getUsuTxExpoToken(), "Pedido criado",
                "Em breve o vendedor aceitará o pedido.");

    }

    @Override
    public Page<PedidoProdutoDadosCompletosDto> listarPedidosDoUsuario(PedidoProdutoFiltroForm filtro, Pageable pageable) {
        Long usuNrId = this.buscarUsuarioAutenticado().getUsuNrId();
        return pedidoProdutoRepository.findAllByUsuNrId(usuNrId, filtro, pageable);
    }

    @Override
    @Transactional
    public List<Long> verificarStatusDoPedido(List<Long> pedNrIds) {

        var usuario = buscarUsuarioAutenticado();

        var listaPedidosCriados = pedidoRepository.findPedidoStatusCriadoByUsuNrId(pedNrIds, usuario.getUsuNrId());

        var listaPedidoParaCancelar = new ArrayList<PedidoEntidade>();
        var listaProdutosParaAtualizarQuantidade = new ArrayList<ProdutoEntidade>();


        LocalDateTime momentoAtual = LocalDateTime.now();

        for (PedidoEntidade pedido : listaPedidosCriados) {
            if (Duration.between(pedido.getPedDtCriado(), momentoAtual).toMinutes() >= 30) {

                pedido.setPedTxStatus(StatusPedidoEnum.CANCELADO);

                var listaProdutosQuantidadeAtualizada = atualizarQuantidadeDoProduto(pedido);
                listaProdutosParaAtualizarQuantidade.addAll(listaProdutosQuantidadeAtualizada);
                listaPedidoParaCancelar.add(pedido);
            }
        }

        pedidoRepository.saveAll(listaPedidoParaCancelar);
        produtoRepository.saveAll(listaProdutosParaAtualizarQuantidade);

        listaPedidoParaCancelar.forEach(produtoEntidade -> {
            enviarNotificacoes(usuario.getUsuTxExpoToken(), "Pedido Cancelado :(",
                    "Sentimos muito pelo incoveniente. Tente realizar o pedido com outro vendedor. Abraços"
            );
        });

        return listaPedidoParaCancelar.stream()
                .map(PedidoEntidade::getPedNrId)
                .toList();
    }

    @Override
    public void atualizarStatusDoPedido(StatusPedidoEnum statusPedidoEnum, Long pedNrId) {

        var pedido = pedidoRepository
                .findById(pedNrId)
                .orElseThrow(() -> new NotFoundException("Pedido não encontrado"));

        pedido.setPedTxStatus(statusPedidoEnum);
        pedidoRepository.save(pedido);
    }

    private BigDecimal calcularValorPedidoProduto(List<ProdutoEntidade> produtos, PedidoProdutoForm form) {

        BigDecimal valor = BigDecimal.ZERO;

        for (ProdutoEntidade produto : produtos) {

            if (produto.getProNrQuantidade() == 0) {
                throw new QuantidadeDeProdutosInsuficenteException(produto.getProTxNome());
            }

            if (produto.getProNrId().equals(form.proNrId())) {

                if (produto.getProNrQuantidade() < form.ppNrQuantidade()) {
                    throw new QuantidadeDeProdutosInsuficenteException(produto.getProTxNome());
                }

                BigDecimal precoProduto = produto.getProNrPreco();
                BigDecimal quantidadeDesejada = BigDecimal.valueOf(form.ppNrQuantidade());
                valor = valor.add(precoProduto.multiply(quantidadeDesejada));
                produto.setProNrQuantidade(produto.getProNrQuantidade() - form.ppNrQuantidade());

                if (produto.getProNrQuantidade() == 0) {
                    produto.setProBlAtivo(false);
                }

                produtoRepository.save(produto);
                break;
            }
        }

        return valor;
    }

    @Async
    protected void enviarNotificacoes(String expoToken, String titulo, String mensagem) {
        expoService.sendPushNotification(expoToken, mensagem, titulo);
    }

    private List<ProdutoEntidade> atualizarQuantidadeDoProduto(PedidoEntidade pedido){

       var listaPedidosProduto = pedidoProdutoRepository.findAllByPedNrId(pedido.getPedNrId());

       var listaProdutos = new ArrayList<ProdutoEntidade>();

       for (var pedidoProduto:listaPedidosProduto){
           var produto = produtoRepository
                   .findById(pedidoProduto.getProNrId())
                   .orElseThrow(() -> new NotFoundException("Produto não encontrado"));

           produto.setProNrQuantidade(produto.getProNrQuantidade() + pedidoProduto.getPpNrQuantidadeProduto());
            listaProdutos.add(produto);
       }

       return listaProdutos;

    }
}
