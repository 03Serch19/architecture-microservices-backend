package com.inmueble_microservice.microservice_1_inmueble.controller;

import com.inmueble_microservice.microservice_1_inmueble.model.Inmueble;
import com.inmueble_microservice.microservice_1_inmueble.service.InmuebleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/inmuebles")
public class InmuebleController {
    @Autowired
    private final InmuebleService inmuebleService;

    @GetMapping
    public ResponseEntity<?> getAllInmuebles() {
        return ResponseEntity.ok(inmuebleService.getAllInmuebles());
    }
    @PostMapping
    public ResponseEntity<?> createInmueble(@RequestBody Inmueble inmueble) {
        return ResponseEntity.ok(inmuebleService.createInmueble(inmueble));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInmueble(@PathVariable Long id) {
        inmuebleService.deleteInmueble(id);
        return ResponseEntity.ok("Inmueble deleted successfully");
        //return new ResponseEntity<>(HttpStatus.OK);
    }

}
