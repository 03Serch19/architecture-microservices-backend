package com.inmueble_microservice.microservice_1_inmueble.service;

import com.inmueble_microservice.microservice_1_inmueble.model.Inmueble;

import java.util.List;

public interface InmuebleService {
    List<Inmueble> getAllInmuebles();

    Inmueble createInmueble(Inmueble inmueble);

    void deleteInmueble(Long id);
}
