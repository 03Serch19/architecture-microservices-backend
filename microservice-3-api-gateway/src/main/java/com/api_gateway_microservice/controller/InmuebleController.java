package com.api_gateway_microservice.controller;

import com.api_gateway_microservice.request.InmuebleServiceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gateway/inmueble")
@RequiredArgsConstructor
public class InmuebleController {
    @Autowired
    private final InmuebleServiceRequest inmuebleServiceRequest;

    @GetMapping
    public ResponseEntity<?> getAllInmuebles() {
        return ResponseEntity.ok(inmuebleServiceRequest.getAllInmuebles());
    }
    @PostMapping
    public ResponseEntity<?> createInmueble(@RequestBody Object inmueble) {
        return ResponseEntity.ok(inmuebleServiceRequest.createInmueble(inmueble));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInmueble(@PathVariable Long id) {
        inmuebleServiceRequest.deleteInmueble(id);
        return ResponseEntity.ok("Inmueble deleted successfully desde Gateway");
    }
}
