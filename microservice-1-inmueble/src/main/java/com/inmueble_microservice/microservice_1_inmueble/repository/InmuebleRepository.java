package com.inmueble_microservice.microservice_1_inmueble.repository;

import com.inmueble_microservice.microservice_1_inmueble.model.Inmueble;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InmuebleRepository extends JpaRepository<Inmueble, Long> {

    // Custom query methods can be defined here if needed
    // For example, to find by name:
    // List<Inmueble> findByNombre(String nombre);
}
