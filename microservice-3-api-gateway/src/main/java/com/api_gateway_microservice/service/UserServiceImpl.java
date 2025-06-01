package com.api_gateway_microservice.service;

import com.api_gateway_microservice.model.Role;
import com.api_gateway_microservice.model.User;
import com.api_gateway_microservice.repository.UserRepository;
import com.api_gateway_microservice.security.jwt.JwtProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final JwtProvider jwtProvider;

    @Override
    public User saveUser(User user) {
        // Encode the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        user.setFechaCreacion(LocalDateTime.now());
         User userCreate = userRepository.save(user);
         userCreate.setToken(jwtProvider.generateToken(userCreate));
        return userCreate;
    }
    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Transactional
    @Override
    public void updateUserRole(String username, Role role) {
        userRepository.updateUserRole(username, role);
    }
    @Override
    public User findByUsernameReturnToken(String username) {
         User user= userRepository.findByUsername(username).orElseThrow(()->
             new UsernameNotFoundException("User not found with username: " + username)
         );
         String jwt= jwtProvider.generateToken(user);

         user.setToken(jwt);
        return user;
    }
}
