package com.api_gateway_microservice.controller;

import com.api_gateway_microservice.model.User;
import com.api_gateway_microservice.service.AuthenticationService;
import com.api_gateway_microservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/authentication")
@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired
    private final AuthenticationService authenticationService;
    @Autowired
    private final UserService userService;

 @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody User user) {
     if(userService.findByUsername(user.getUsername()).isPresent()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
     }
        User savedUser = userService.saveUser(user);
        //return ResponseEntity.ok(savedUser);
     return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody User user) {

        return new ResponseEntity<>(authenticationService.signInAndReturnJWT(user), HttpStatus.OK);
       // return ResponseEntity.ok(authenticationService.signInAndReturnJWT(user));
    }
}
