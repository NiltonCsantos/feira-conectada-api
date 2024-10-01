package org.feiraconectada.feiraconectadaapi.service.financeiro.estoque.impl;

import lombok.RequiredArgsConstructor;
import org.feiraconectada.feiraconectadaapi.exceptions.NotFoundException;
import org.feiraconectada.feiraconectadaapi.model.financeiro.EstoqueEntidade;
import org.feiraconectada.feiraconectadaapi.repository.autenticacao.UsuarioRepository;
import org.feiraconectada.feiraconectadaapi.repository.financeiro.EstoqueRepository;
import org.feiraconectada.feiraconectadaapi.repository.financeiro.NichoRepository;
import org.feiraconectada.feiraconectadaapi.repository.financeiro.VendedorRepository;
import org.feiraconectada.feiraconectadaapi.service.base.impl.BaseServiceImpl;
import org.feiraconectada.feiraconectadaapi.service.financeiro.estoque.EstoqueService;
import org.feiraconectada.feiraconectadaapi.service.financeiro.estoque.dto.EstoqueDto;
import org.feiraconectada.feiraconectadaapi.service.financeiro.estoque.form.EstoqueForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EstoqueServiceImpl extends BaseServiceImpl implements EstoqueService {

    private final VendedorRepository vendedorRepository;
    private final EstoqueRepository estoqueRepository;
    private final UsuarioRepository usuarioRepository;
    private final NichoRepository nichoRepository;

    @Override
    public void cadastrarOuAtualizarEstoque( EstoqueForm form) {

        nichoRepository.findById(form.nicNrId())
                .orElseThrow(() -> new NotFoundException("Nicho não encontrado"));

        var venNrId = this.buscarUsuarioAutenticado().getId();

        final var estoque = (form.estNrId() != null)
                ? estoqueRepository.findById(form.estNrId())
                .orElseThrow(() -> new NotFoundException("Estoque não encontrado"))
                : new EstoqueEntidade();

        estoque.setEstTxNome(form.estTxNome());
        estoque.setVenNrId(venNrId);
        estoque.setNicNrId(form.nicNrId());

        estoqueRepository.save(estoque);
    }

    @Override
    public EstoqueDto buscarEstoquePorId(Long estNrId) {

        var estoque= this.estoqueRepository.findById(estNrId)
                .orElseThrow(() -> new NotFoundException("Estoque não encontrado"));

        return new EstoqueDto(estoque);
    }

    @Override
    public Page<EstoqueDto> listarEstoquePorNicho(Long nicNrId, Pageable pageable) {
        return estoqueRepository.listarEstoquesPorNicho(nicNrId, pageable).map(EstoqueDto::new);
    }

    //
//    @Override
//    public Page<EstoqueDto> buscarEstoqueDoVendedor(Long venNrId, Pageable pageable){
//       return  this.estoqueRepository.listarEstoques(venNrId, pageable).map(EstoqueDto::new);
//    }
}
