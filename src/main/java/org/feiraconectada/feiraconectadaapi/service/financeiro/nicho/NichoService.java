package org.feiraconectada.feiraconectadaapi.service.financeiro.nicho;

import org.feiraconectada.feiraconectadaapi.service.financeiro.nicho.dto.NichoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface NichoService {
    Page<NichoDto>listarNichoDosEstoquesPorIdVendedor(Long venNrId, Pageable pageable);
}
