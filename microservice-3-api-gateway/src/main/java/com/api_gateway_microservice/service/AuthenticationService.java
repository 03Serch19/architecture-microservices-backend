package com.api_gateway_microservice.service;

import com.api_gateway_microservice.model.User;

public interface AuthenticationService {
    User signInAndReturnJWT(User signInRequest);
}
