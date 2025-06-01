package com.api_gateway_microservice.service;

import com.api_gateway_microservice.model.Role;
import com.api_gateway_microservice.model.User;

import java.util.Optional;

public interface UserService {
    User saveUser(User user);

    Optional<User> findByUsername(String username);

    void updateUserRole(String username, Role role);

    User findByUsernameReturnToken(String username);
}
