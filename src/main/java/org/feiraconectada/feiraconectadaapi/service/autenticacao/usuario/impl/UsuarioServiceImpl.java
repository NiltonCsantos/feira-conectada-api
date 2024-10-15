package org.feiraconectada.feiraconectadaapi.service.autenticacao.usuario.impl;

import lombok.RequiredArgsConstructor;
import org.feiraconectada.feiraconectadaapi.exceptions.NotFoundException;
import org.feiraconectada.feiraconectadaapi.model.endereco.UsuarioEnderecoEntidade;
import org.feiraconectada.feiraconectadaapi.repository.autenticacao.UsuarioRepository;
import org.feiraconectada.feiraconectadaapi.repository.endereco.EnderecoRepository;
import org.feiraconectada.feiraconectadaapi.repository.endereco.UsuarioEnderecoRepository;
import org.feiraconectada.feiraconectadaapi.repository.financeiro.VendedorRepository;
import org.feiraconectada.feiraconectadaapi.service.autenticacao.usuario.dto.ExpoTokenForm;
import org.feiraconectada.feiraconectadaapi.service.autenticacao.usuario.dto.UsuarioDto;
import org.feiraconectada.feiraconectadaapi.service.autenticacao.usuario.form.UsuarioEdicaoForm;
import org.feiraconectada.feiraconectadaapi.service.endereco.dto.EnderecoDto;
import org.feiraconectada.feiraconectadaapi.service.autenticacao.usuario.UsuarioService;
import org.feiraconectada.feiraconectadaapi.service.base.impl.BaseServiceImpl;
import org.feiraconectada.feiraconectadaapi.service.financeiro.vendedor.dto.VendedorDadosBasicosDto;
import org.feiraconectada.feiraconectadaapi.service.financeiro.vendedor.dto.VendedorDadosCompletoDto;
import org.feiraconectada.feiraconectadaapi.service.financeiro.vendedor.form.VendedorFiltrosForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl extends BaseServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final EnderecoRepository enderecoRepository;
    private final UsuarioEnderecoRepository usuarioEnderecoRepository;


    @Override
    public void adicionarEnderecoAoUsuario(Long endNrId){

        var usuNrId = this.buscarUsuarioAutenticado().getUsuNrId();
        enderecoRepository.findById(endNrId)
                .orElseThrow(() -> new NotFoundException("Endereço não encontrado"));

        var usuarioEnderecoEntidade = UsuarioEnderecoEntidade
                .builder()
                .endNrId(endNrId)
                .usuNrId(usuNrId)
                .build();
        usuarioEnderecoRepository.save(usuarioEnderecoEntidade);
    }

    @Override
    public Page<EnderecoDto> buscarEnderecosDoUsuario(Pageable pageable) {
        var usuNrId = this.buscarUsuarioAutenticado().getUsuNrId();
        return enderecoRepository.listarEnderecosdoUsuario(usuNrId, pageable).map(EnderecoDto::new);
    }

    @Override
    public Page<VendedorDadosBasicosDto> listarFeirantesComImagem(VendedorFiltrosForm filtro, Pageable pageable) {
        return usuarioRepository.listarVendedoresComImagem(filtro, pageable);
    }

    @Override
    public UsuarioDto editarDadosUsuario(UsuarioEdicaoForm form) {
        var usuario = this.buscarUsuarioAutenticado();
        usuario.setUsuTxNome(form.usuTxNome().trim());
        usuarioRepository.save(usuario);
        return new UsuarioDto(usuario);
    }

    @Override
    public void realizarCompra(List<Long> proNrIds) {

    }

    @Override
    public void atualizarTokenDeNotificacao(ExpoTokenForm form) {
        var usuario = this.buscarUsuarioAutenticado();
        if (usuario.getUsuTxExpoToken()==null || usuario.getUsuTxExpoToken().equals(form.token())){
            usuario.setUsuTxExpoToken(form.token());
            usuarioRepository.save(usuario);
        }
    }
}
