package org.feiraconectada.feiraconectadaapi.service;


import org.feiraconectada.feiraconectadaapi.dto.request.UserLogin;
import org.feiraconectada.feiraconectadaapi.dto.request.UserRegister;
import org.feiraconectada.feiraconectadaapi.model.UserModel;
import org.feiraconectada.feiraconectadaapi.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;


@Service
@EnableAsync
public class AuthenticateService implements IAuthenticationService {


   private final UserRepository userRepository;

   private  final AuthenticationManager authenticationManager;

   private final TokenService tokenService;

   private final AuthorizationService authorizationService;

   private final MailService emailService;




    public AuthenticateService(UserRepository userRepository, AuthenticationManager authenticationManager,
                               TokenService tokenService, AuthorizationService authorizationService,
                               MailService emailService) {

        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.authorizationService = authorizationService;
        this.emailService = emailService;


    }

    @Override
    public ResponseEntity save(UserRegister user) {

        try {

            if (this.userRepository.findByEmail(user.email())!=null){
                throw new Exception("Usuário já cadastrado");
            }

            String encryptedPassword= new BCryptPasswordEncoder().encode(user.password());


            UserModel newUser= new UserModel(user, encryptedPassword);

//            System.out.println(2/0);

            userRepository.save(newUser);

            System.out.println("Enviando email");



            emailService.sendMail(user.email(), user.fullName());

            return  ResponseEntity.ok().body(user);

        }catch (Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());

        }


    }

    @Override
    public ResponseEntity connnect(UserLogin user) {

        if (authorizationService.loadUserByUsername(user.email())==null){
            return ResponseEntity.badRequest().body("Usuário não encontrado");
        }else{

            UsernamePasswordAuthenticationToken passwordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    user.email(), user.password());

            var auth=this.authenticationManager.authenticate(passwordAuthenticationToken);

            String token= this.tokenService.generateToken((UserModel) auth.getPrincipal());

            return ResponseEntity.ok().body(token);

        }
    }
}
