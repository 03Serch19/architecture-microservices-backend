package com.api_gateway_microservice.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    @Autowired
    private /*final*/ JwtProvider jwtProvider;
    /*public JwtAuthorizationFilter(JwtProvider jwtProvider) {//SI HAREMOS USO DE  LOOMMBOS PRACON REQUIREEDARRCONSTERUCTOR Y FINAL NUESTROCAPO, NOAHCE FALLTA CRAR ESTO
        this.jwtProvider = jwtProvider;
    }*/

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
      Authentication authentication = jwtProvider.getAuthentication(request);
        if (authentication != null && jwtProvider.isValid(request)) {
            //authentication.setAuthenticated(true);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
