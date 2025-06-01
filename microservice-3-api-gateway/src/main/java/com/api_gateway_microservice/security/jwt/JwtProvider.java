package com.api_gateway_microservice.security.jwt;

import com.api_gateway_microservice.model.User;
import com.api_gateway_microservice.security.UserMain;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

public interface JwtProvider {

    String generateToken(UserMain auth);

    String generateToken(User userRegi);

    boolean isValid(HttpServletRequest request);

    Authentication getAuthentication(HttpServletRequest request);
}
