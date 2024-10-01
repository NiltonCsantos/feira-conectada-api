package org.feiraconectada.feiraconectadaapi.service.base.impl;

import org.feiraconectada.feiraconectadaapi.exceptions.NotFoundException;
import org.feiraconectada.feiraconectadaapi.model.autenticacao.UsuarioEntidade;
import org.feiraconectada.feiraconectadaapi.service.base.BaseService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class BaseServiceImpl implements BaseService {
    @Override
    public UsuarioEntidade buscarUsuarioAutenticado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UsuarioEntidade) {
            return (UsuarioEntidade) authentication.getPrincipal();
        }
        throw new NotFoundException("Usuario não encontrado");
    }
}
