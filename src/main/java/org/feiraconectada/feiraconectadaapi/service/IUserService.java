package org.feiraconectada.feiraconectadaapi.service;

import org.feiraconectada.feiraconectadaapi.dto.request.UserLogin;
import org.feiraconectada.feiraconectadaapi.dto.request.UserRegister;
import org.springframework.http.ResponseEntity;

public interface IUserService {

    ResponseEntity save(UserRegister user);

    ResponseEntity connnect(UserLogin user);

}
