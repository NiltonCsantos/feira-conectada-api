package org.feiraconectada.feiraconectadaapi.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.feiraconectada.feiraconectadaapi.repository.UserRepository;
import org.feiraconectada.feiraconectadaapi.service.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    private final  UserRepository userRepository;
    public SecurityFilter(TokenService tokenService, UserRepository userRepository) {

        this.tokenService=tokenService;

        this.userRepository=userRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token=this.recoveryToken(request);

        if (token != null){

            String email= tokenService.validateToken(token);

            UserDetails user= userRepository.findByEmail(email);

            var auth= new UsernamePasswordAuthenticationToken(user, null);

            SecurityContextHolder.getContext().setAuthentication(auth);



        }

        filterChain.doFilter(request, response);
    }

    private String recoveryToken(HttpServletRequest request){

        String authHeader= request.getHeader("Authorization");

        return authHeader==null
                ? null
                :authHeader.replace("Bearer", "");





    }
}
