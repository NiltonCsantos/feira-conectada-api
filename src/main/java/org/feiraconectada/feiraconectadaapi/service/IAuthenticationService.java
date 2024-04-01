package org.feiraconectada.feiraconectadaapi.service;

import org.feiraconectada.feiraconectadaapi.dto.request.UserLogin;
import org.feiraconectada.feiraconectadaapi.dto.request.UserRegister;
import org.feiraconectada.feiraconectadaapi.dto.response.AuthResponse;
import org.feiraconectada.feiraconectadaapi.dto.response.UserResponse;
import org.springframework.http.ResponseEntity;

public interface IAuthenticationService {

    void save(UserRegister user);

    AuthResponse connnect(UserLogin user);

    AuthResponse loginOnToken(String refreshToken);
}
