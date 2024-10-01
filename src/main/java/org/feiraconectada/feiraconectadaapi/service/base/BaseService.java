package org.feiraconectada.feiraconectadaapi.service.base;

import org.feiraconectada.feiraconectadaapi.model.autenticacao.UsuarioEntidade;

public interface BaseService {
    UsuarioEntidade buscarUsuarioAutenticado();
}
