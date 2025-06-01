package com.api_gateway_microservice.service;

import com.api_gateway_microservice.model.User;
import com.api_gateway_microservice.security.UserMain;
import com.api_gateway_microservice.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements  AuthenticationService{
    @Autowired
    private final AuthenticationManager authenticationManager;
    @Autowired
    private final JwtProvider jwtProvider;

    @Override
    public User signInAndReturnJWT(User signInRequest) {

        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));
        UserMain userMain=(UserMain) authentication.getPrincipal();
        String jwt=jwtProvider.generateToken(userMain);
        User sigInUse = userMain.getUser();
        sigInUse.setToken(jwt);
        return sigInUse;
    }
}
