package org.feiraconectada.feiraconectadaapi.service;


import org.feiraconectada.feiraconectadaapi.dto.request.UserLogin;
import org.feiraconectada.feiraconectadaapi.dto.request.UserRegister;
import org.feiraconectada.feiraconectadaapi.model.UserModel;
import org.feiraconectada.feiraconectadaapi.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticateService implements  IUserService {


   private final UserRepository userRepository;

   private  final AuthenticationManager authenticationManager;

   private final TokenService tokenService;


    public AuthenticateService(UserRepository userRepository, AuthenticationManager authenticationManager,
                               TokenService tokenService) {

        this.userRepository = userRepository;
        this.authenticationManager=authenticationManager;
        this.tokenService=tokenService;
    }


    @Override
    public ResponseEntity save(UserRegister user) {

        if (this.userRepository.findByEmail(user.email())!=null){
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword= new BCryptPasswordEncoder().encode(user.password());


        UserModel newUser= new UserModel(user, encryptedPassword);

        userRepository.save(newUser);

        return  ResponseEntity.ok().body(user);


    }

    @Override
    public ResponseEntity connnect(UserLogin user) {

        if (this.userRepository.findByEmail(user.email())==null){
            return ResponseEntity.badRequest().body("Usuário não encontrado");
        }else{
            UsernamePasswordAuthenticationToken passwordAuthenticationToken = new UsernamePasswordAuthenticationToken(user.email(), user.password());
            var auth=this.authenticationManager.authenticate(passwordAuthenticationToken);
            System.out.println("Token gerado: ");
            System.out.println(tokenService.generateToken(user));
            System.out.println("Teste validação:");

            return ResponseEntity.ok().body(user);
        }
    }
}
