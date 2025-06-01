package com.compra_microservice.controller;

import com.compra_microservice.model.Compra;
import com.compra_microservice.service.CompraService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/compras")
public class CompraController {
    @Autowired
    private final CompraService compraService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getAllComprasOfUser(@PathVariable Long userId) {
        return ResponseEntity.ok(compraService.findAllComprasOfUser(userId));
    }
    @PostMapping
    public ResponseEntity<?> createCompra(@RequestBody Compra compra) {
        return ResponseEntity.ok(compraService.saveCompra(compra));
    }
}
