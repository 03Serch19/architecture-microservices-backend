package com.api_gateway_microservice.controller;

import com.api_gateway_microservice.request.ComprasServiceRequest;
import com.api_gateway_microservice.security.UserMain;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gateway/compras")
@RequiredArgsConstructor

public class CompraController {
    @Autowired
    private final ComprasServiceRequest compraServiceRequest;

    @GetMapping
    public ResponseEntity<?> getAllComprasOfUser(@AuthenticationPrincipal UserMain userMain) {
        return ResponseEntity.ok(compraServiceRequest.getAllComprasOfUserGate(userMain.getId()));
    }

    @PostMapping
    public ResponseEntity<?> createCompra(@RequestBody Object compra) {
        return ResponseEntity.ok(compraServiceRequest.createCompraGate(compra));
    }


}
