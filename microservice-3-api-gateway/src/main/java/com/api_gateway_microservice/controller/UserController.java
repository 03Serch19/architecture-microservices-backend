package com.api_gateway_microservice.controller;

import com.api_gateway_microservice.model.Role;
import com.api_gateway_microservice.security.UserMain;
import com.api_gateway_microservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
    @RequestMapping("/api/v1/user")
    @RequiredArgsConstructor
    public class UserController {
        @Autowired
        private final UserService userService;

        @PutMapping("/{role}")
        public ResponseEntity<?> changeRole(@AuthenticationPrincipal UserMain userMain, @PathVariable Role role) {
            userService.updateUserRole(userMain.getUsername(), role);
            return ResponseEntity.ok("Role updated successfully");
        }
        @GetMapping//retorna el usaurio en sesion con un token
        public ResponseEntity<?> getUserDetailsInToken(@AuthenticationPrincipal UserMain userMain) {
            return ResponseEntity.ok(userService.findByUsernameReturnToken(userMain.getUsername()));
        }
    }
