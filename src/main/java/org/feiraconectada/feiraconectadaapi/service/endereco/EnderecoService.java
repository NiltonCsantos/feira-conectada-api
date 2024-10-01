package org.feiraconectada.feiraconectadaapi.service.endereco;

import org.feiraconectada.feiraconectadaapi.service.endereco.dto.EnderecoDto;
import org.feiraconectada.feiraconectadaapi.service.endereco.form.EnderecoFiltrosForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EnderecoService {
    Page<EnderecoDto> listarEnderecos(EnderecoFiltrosForm filtro, Pageable pageable);
    EnderecoDto buscarEnderecoPorId(Long endNrId);
}
