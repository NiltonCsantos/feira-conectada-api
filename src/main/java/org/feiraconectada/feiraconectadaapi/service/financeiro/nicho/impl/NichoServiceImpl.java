package org.feiraconectada.feiraconectadaapi.service.financeiro.nicho.impl;

import lombok.RequiredArgsConstructor;
import org.feiraconectada.feiraconectadaapi.repository.financeiro.NichoRepository;
import org.feiraconectada.feiraconectadaapi.service.financeiro.nicho.NichoService;
import org.feiraconectada.feiraconectadaapi.service.financeiro.nicho.dto.NichoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NichoServiceImpl implements NichoService {

    private final NichoRepository nichoRepository;
    @Override
    public Page<NichoDto> listarNichoDosEstoquesPorIdVendedor(Long venNrId, Pageable pageable) {
        return nichoRepository.listarNichosDosEstoquesPoridDoVendedor(venNrId, pageable).map(NichoDto::new);
    }
}
