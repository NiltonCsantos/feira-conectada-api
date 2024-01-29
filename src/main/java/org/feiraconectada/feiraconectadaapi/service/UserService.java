package org.feiraconectada.feiraconectadaapi.service;


import org.feiraconectada.feiraconectadaapi.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public ResponseEntity findUsers(){



       return ResponseEntity.ok().body(    userRepository.findAll());

    }

}
