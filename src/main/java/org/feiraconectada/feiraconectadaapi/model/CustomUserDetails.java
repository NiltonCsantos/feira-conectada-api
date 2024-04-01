package org.feiraconectada.feiraconectadaapi.model;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface CustomUserDetails extends UserDetails {

    Integer getId();
    String getEmail();

//
    List<UserAddresModel> getUserAddresList();



}
