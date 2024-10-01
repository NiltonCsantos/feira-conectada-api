package org.feiraconectada.feiraconectadaapi.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.feiraconectada.feiraconectadaapi.service.autenticacao.authenticate.UserDetailsServiceImpl;
import org.feiraconectada.feiraconectadaapi.service.autenticacao.token.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;

    private final UserDetailsService userDetailsServiceImpl;
    public SecurityFilter(TokenService tokenService, UserDetailsServiceImpl userDetailsServiceImpl) {

        this.tokenService=tokenService;

        this.userDetailsServiceImpl = userDetailsServiceImpl;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token=this.recoveryToken(request);

        if (token != null){

            String email= tokenService.validarToken(token);

            UserDetails user= userDetailsServiceImpl.loadUserByUsername(email);

            var auth= new UsernamePasswordAuthenticationToken(user, email, user.getAuthorities());

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
