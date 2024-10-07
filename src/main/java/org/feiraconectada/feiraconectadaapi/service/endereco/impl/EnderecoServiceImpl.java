package org.feiraconectada.feiraconectadaapi.service.endereco.impl;

import lombok.RequiredArgsConstructor;
import org.feiraconectada.feiraconectadaapi.exceptions.NotFoundException;
import org.feiraconectada.feiraconectadaapi.repository.endereco.EnderecoRepository;
import org.feiraconectada.feiraconectadaapi.service.base.impl.BaseServiceImpl;
import org.feiraconectada.feiraconectadaapi.service.endereco.EnderecoService;
import org.feiraconectada.feiraconectadaapi.service.endereco.dto.EnderecoDto;
import org.feiraconectada.feiraconectadaapi.service.endereco.form.EnderecoFiltrosForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnderecoServiceImpl extends BaseServiceImpl implements EnderecoService {

    private final EnderecoRepository enderecoRepository;

    @Override
    public Page<EnderecoDto> listarEnderecos(EnderecoFiltrosForm filtro, Pageable pageable) {
        var usuNrId = this.buscarUsuarioAutenticado().getUsuNrId();
        return enderecoRepository.listarenderecos(filtro, usuNrId, pageable).map(EnderecoDto::new);
    }

    @Override
    public EnderecoDto buscarEnderecoPorId(Long endNrId) {
        var endereco = enderecoRepository.findById(endNrId)
                .orElseThrow(() -> new NotFoundException("Endereço não encontrado"));
        return new EnderecoDto(endereco);
    }
}
