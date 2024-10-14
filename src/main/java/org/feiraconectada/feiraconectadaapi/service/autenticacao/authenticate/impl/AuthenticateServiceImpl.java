package org.feiraconectada.feiraconectadaapi.service.autenticacao.authenticate.impl;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.feiraconectada.feiraconectadaapi.exceptions.NotFoundException;
import org.feiraconectada.feiraconectadaapi.exceptions.RegistredUserException;
import org.feiraconectada.feiraconectadaapi.model.autenticacao.CustomUserDetails;
import org.feiraconectada.feiraconectadaapi.model.autenticacao.UsuarioEntidade;
import org.feiraconectada.feiraconectadaapi.model.financeiro.ImagemVendedorEntidade;
import org.feiraconectada.feiraconectadaapi.model.financeiro.VendedorEntidade;
import org.feiraconectada.feiraconectadaapi.repository.autenticacao.PerfilRepository;
import org.feiraconectada.feiraconectadaapi.repository.autenticacao.UsuarioRepository;
import org.feiraconectada.feiraconectadaapi.repository.financeiro.ImagemVendedorRepository;
import org.feiraconectada.feiraconectadaapi.repository.financeiro.NichoRepository;
import org.feiraconectada.feiraconectadaapi.repository.financeiro.VendedorRepository;
import org.feiraconectada.feiraconectadaapi.service.autenticacao.authenticate.AuthenticationService;
import org.feiraconectada.feiraconectadaapi.service.autenticacao.authenticate.UserDetailsServiceImpl;
import org.feiraconectada.feiraconectadaapi.service.autenticacao.authenticate.dto.AuthDto;
import org.feiraconectada.feiraconectadaapi.service.autenticacao.usuario.dto.UsuarioDto;
import org.feiraconectada.feiraconectadaapi.service.autenticacao.usuario.form.FeiranteRegistroForm;
import org.feiraconectada.feiraconectadaapi.service.autenticacao.usuario.form.UsuarioLoginForm;
import org.feiraconectada.feiraconectadaapi.service.autenticacao.usuario.form.UsuarioRegistroForm;
import org.feiraconectada.feiraconectadaapi.service.mail.MailService;
import org.feiraconectada.feiraconectadaapi.service.autenticacao.token.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticateServiceImpl implements AuthenticationService {


   private final UsuarioRepository usuarioRepository;
   private final AuthenticationManager authenticationManager;
   private final PerfilRepository perfilRepository;
   private final VendedorRepository vendedorRepository;
   private final TokenService tokenService;
   private final UserDetailsServiceImpl authorizationService;
   private final MailService emailService;
   private final ImagemVendedorRepository imagemVendedorRepository;
   private final NichoRepository nichoRepository;


    @Override
    public void salvarUsuario(UsuarioRegistroForm form) {

        //todo: melhorar isso aqui
        if (this.usuarioRepository.findByUsuTxEmail(form.usuTxEmail())!=null){
            throw new RegistredUserException();
        }

        var perfil = perfilRepository.findByPerfilUsuario()
                .orElseThrow();

        String encryptedPassword= new BCryptPasswordEncoder().encode(form.usuTxSenha());

                var usuario= UsuarioEntidade
                        .builder()
                        .perfil(perfil)
                        .usuTxEmail(form.usuTxEmail().trim())
                        .usuTxNome(form.usuTxNome().trim())
                        .usuTxSenha(encryptedPassword.trim())
                        .build();

                usuarioRepository.save(usuario);

                var token = tokenService.gerarTokenTemporario(usuario);

                emailService.sendMail(usuario.getUsuTxEmail(), usuario.getUsername(), token);
    }

    @Override
    @Transactional
    public void salvarFeirante(FeiranteRegistroForm form){

            if (this.usuarioRepository.findByUsuTxEmail(form.usuTxEmail())!=null){
                throw new RegistredUserException();
            }

        nichoRepository.findById(form.nicNrId())
                .orElseThrow(() -> new NotFoundException("Nicho não encontrado"));

            String encryptedPassword= new BCryptPasswordEncoder().encode(form.usuTxSenha());

            var perfil = perfilRepository.findByPerfilVendedor()
                .orElseThrow();

            var usuario = UsuarioEntidade.builder()
                    .usuTxNome(form.usuTxNome().trim())
                    .usuTxSenha(encryptedPassword.trim())
                    .usuTxEmail(form.usuTxEmail().trim())
                    .perfil(perfil)
                    .build();

            var usuarioSalvo = usuarioRepository.save(usuario);


            var vendedor= new VendedorEntidade(form, encryptedPassword);


            vendedor.setVenNrId(usuarioSalvo.getUsuNrId());

            if (form.ivTxImagem()!=null){
                var imagemVendedor = ImagemVendedorEntidade.builder()
                        .imgVenTxImagem(form.ivTxImagem())
                        .build();

                var ivNrId = imagemVendedorRepository.save(imagemVendedor).getIvNrId();

                vendedor.setIvNrId(ivNrId);
            }

            vendedorRepository.save(vendedor);

            var token = tokenService.gerarTokenTemporario(usuario);

            emailService.sendMail(usuarioSalvo.getEmail(), usuarioSalvo.getUsuTxNome(), token);
    }

    @Override
    public AuthDto fazerLogin(UsuarioLoginForm form) {

        if (authorizationService.loadUserByUsername(form.usuTxEmail())==null){
            throw new NotFoundException("Usuário não encontrado");
        }

            UsernamePasswordAuthenticationToken passwordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    form.usuTxEmail(), form.usuTxSenha());

            var auth=this.authenticationManager.authenticate(passwordAuthenticationToken);

            var claims = preencherClaims(new UsuarioDto((UsuarioEntidade) auth.getPrincipal()));

            String acessToken= this.tokenService.gerarToken((UserDetails) auth.getPrincipal(), claims);

            String refreshToken=this.tokenService.gerarRefreshToken((UserDetails) auth.getPrincipal());

            return new AuthDto(acessToken, refreshToken);
    }

    //verificar
    @Override
    public AuthDto fazerLoginComToken(String refrashToken) throws NotFoundException {

        String email= tokenService.validarToken(refrashToken);

        CustomUserDetails user= usuarioRepository.findByUsuTxEmail(email);

        if (user==null){
            throw  new NotFoundException("Ocorreu um erro ao buscar o usuário");
        }

        var claims = preencherClaims(new UsuarioDto((UsuarioEntidade) user));

        String acessToken= this.tokenService.gerarToken((UserDetails) user, claims);

        String refreshToken=this.tokenService.gerarRefreshToken((UserDetails) user);

        return new AuthDto(acessToken, refreshToken);
    }


    private Map<String, Object> preencherClaims(UsuarioDto usario){
        Map<String, Object> claims = new HashMap<>();
        claims.put("usuario",usario);
        return claims;
    }

}
