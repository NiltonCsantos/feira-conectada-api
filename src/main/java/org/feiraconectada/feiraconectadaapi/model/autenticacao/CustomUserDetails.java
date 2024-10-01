package org.feiraconectada.feiraconectadaapi.model.autenticacao;


import org.springframework.security.core.userdetails.UserDetails;


public interface CustomUserDetails extends UserDetails {

    Long getId();
    String getEmail();

}
