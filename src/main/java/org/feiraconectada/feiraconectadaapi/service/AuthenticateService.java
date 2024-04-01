package org.feiraconectada.feiraconectadaapi.service;


import org.aspectj.weaver.ast.Not;
import org.feiraconectada.feiraconectadaapi.dto.request.UserAdmin;
import org.feiraconectada.feiraconectadaapi.dto.request.UserLogin;
import org.feiraconectada.feiraconectadaapi.dto.request.UserRegister;
import org.feiraconectada.feiraconectadaapi.dto.response.AuthResponse;
import org.feiraconectada.feiraconectadaapi.dto.response.UserResponse;
import org.feiraconectada.feiraconectadaapi.enuns.UserRole;
import org.feiraconectada.feiraconectadaapi.exceptions.InternalErrorException;
import org.feiraconectada.feiraconectadaapi.exceptions.RegistredUserException;
import org.feiraconectada.feiraconectadaapi.exceptions.NotFoundException;
import org.feiraconectada.feiraconectadaapi.model.*;
import org.feiraconectada.feiraconectadaapi.repository.SellerRepository;
import org.feiraconectada.feiraconectadaapi.repository.UserRepository;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@EnableAsync
public class AuthenticateService implements IAuthenticationService {


   private final UserRepository userRepository;

   private final SellerRepository sellerRepository;

   private  final AuthenticationManager authenticationManager;

   private final TokenService tokenService;

   private final AuthorizationService authorizationService;

   private final MailService emailService;


    public AuthenticateService(UserRepository userRepository, AuthenticationManager authenticationManager,
                               TokenService tokenService, AuthorizationService authorizationService,
                               MailService emailService, SellerRepository sellerRepository) {

        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.authorizationService = authorizationService;
        this.emailService = emailService;
        this.sellerRepository=sellerRepository;

    }

    @Override
    public void save(UserRegister user) {

        System.out.println("Registrando");

            try {
                if (this.userRepository.findByEmail(user.email())!=null){
                    throw new RegistredUserException();
                }

                String encryptedPassword= new BCryptPasswordEncoder().encode(user.password());

                UserModel newUser= new UserModel(user, encryptedPassword);

                userRepository.save(newUser);
                emailService.sendMail(user.email(), user.fullName());


            }catch (InternalErrorException e){
                throw new InternalErrorException();
            }





    }

    public void saveAdmin(UserAdmin admin){

        try {
            if (this.userRepository.findByEmail(admin.email())!=null){
                throw new RegistredUserException();
            }



            System.out.println("Admin: ");
            System.out.println(admin);

            String encryptedPassword= new BCryptPasswordEncoder().encode(admin.password());

            SellerModel newUser= new SellerModel(admin, encryptedPassword);

            sellerRepository.save(newUser);

            emailService.sendMail(admin.email(), admin.fullName());


        }catch (InternalErrorException e){
            throw new InternalErrorException();
        }



    }

    @Override
    public AuthResponse connnect(UserLogin user) {

        CustomUserDetails userDetails= userRepository.findByEmail(user.email());



        if (authorizationService.loadUserByUsername(user.email())==null){
            throw new NotFoundException("Usuário não encontrado");
        }

            UsernamePasswordAuthenticationToken passwordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    user.email(), user.password());

            var auth=this.authenticationManager.authenticate(passwordAuthenticationToken);

            String acessToken= this.tokenService.generateToken((UserModel) auth.getPrincipal());

            String refreshToken=this.tokenService.generateRefrashToken((UserModel) auth.getPrincipal());

            System.out.println("Refresh Token");

            System.out.println(refreshToken);

            return new AuthResponse(new UserResponse(userDetails.getId(), userDetails.getUsername(), userDetails.getEmail()),
                    acessToken, refreshToken);


    }

    //verificar
    @Override
    public AuthResponse loginOnToken(String refrashToken) throws NotFoundException {

        String email= tokenService.validateRefrashToken(refrashToken);

        CustomUserDetails user= userRepository.findByEmail(email);


        if (user==null){
            throw  new NotFoundException("Ocorreu um erro ao buscar o usuário");
        }

        String acessToken= this.tokenService.generateToken((UserModel) user);

        String refreshToken=this.tokenService.generateRefrashToken((UserModel) user);

        return new AuthResponse(new UserResponse(user.getId(), user.getUsername(), user.getEmail()),
                acessToken, refreshToken);


    }


}
