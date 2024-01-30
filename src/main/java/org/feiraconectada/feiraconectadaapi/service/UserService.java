package org.feiraconectada.feiraconectadaapi.service;


import org.feiraconectada.feiraconectadaapi.dto.request.UserFind;
import org.feiraconectada.feiraconectadaapi.dto.response.UserResponse;
import org.feiraconectada.feiraconectadaapi.exceptions.UserNotFound;
import org.feiraconectada.feiraconectadaapi.model.UserModel;
import org.feiraconectada.feiraconectadaapi.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public ResponseEntity findUser(String email){


        UserDetails userDetails= userRepository.findByEmail(email);

        if (userDetails==null){

            throw new UserNotFound("Usuário não encontrado");

        }else{
            UserResponse userResponse= new UserResponse(userDetails.getUsername());
            return ResponseEntity.ok().body(  userResponse);
        }




    }

}
