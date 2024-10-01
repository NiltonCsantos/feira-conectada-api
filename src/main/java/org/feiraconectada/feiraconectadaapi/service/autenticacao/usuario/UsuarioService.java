package org.feiraconectada.feiraconectadaapi.service.autenticacao.usuario;

import org.feiraconectada.feiraconectadaapi.service.autenticacao.usuario.dto.UsuarioDto;
import org.feiraconectada.feiraconectadaapi.service.autenticacao.usuario.form.UsuarioEdicaoForm;
import org.feiraconectada.feiraconectadaapi.service.endereco.dto.EnderecoDto;
import org.feiraconectada.feiraconectadaapi.service.financeiro.vendedor.dto.VendedorDadosBasicosDto;
import org.feiraconectada.feiraconectadaapi.service.financeiro.vendedor.dto.VendedorDadosCompletoDto;
import org.feiraconectada.feiraconectadaapi.service.financeiro.vendedor.form.VendedorFiltrosForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UsuarioService {
    void adicionarEnderecoAoUsuario(Long endNrId);
    Page<EnderecoDto> buscarEnderecosDoUsuario(Pageable pageable);
    Page<VendedorDadosBasicosDto> listarFeirantesComImagem(VendedorFiltrosForm filtro, Pageable pageable);
    UsuarioDto editarDadosUsuario(UsuarioEdicaoForm usuarioEdicaoForm);
    void realizarCompra(List<Long> proNrIds);
}
