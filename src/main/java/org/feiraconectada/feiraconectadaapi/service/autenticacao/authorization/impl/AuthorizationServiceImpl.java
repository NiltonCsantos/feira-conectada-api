//package org.feiraconectada.feiraconectadaapi.service.authorization.impl;
//
//import org.feiraconectada.feiraconectadaapi.model.user.UsuarioEntidade;
//import org.feiraconectada.feiraconectadaapi.service.authorization.AuthorizationService;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AuthorizationServiceImpl implements AuthorizationService {
//    @Override
//    public UsuarioEntidade getUser() {
//
//        var usuarioAuthenticado = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        if (usuarioAuthenticado instanceof UsuarioEntidade) {
//            return (UsuarioEntidade) usuarioAuthenticado;
//        }
//
//        throw new RuntimeException();
//    }
//}
