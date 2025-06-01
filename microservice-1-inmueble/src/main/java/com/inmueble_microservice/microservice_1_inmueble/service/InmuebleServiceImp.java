package com.inmueble_microservice.microservice_1_inmueble.service;

import com.inmueble_microservice.microservice_1_inmueble.model.Inmueble;
import com.inmueble_microservice.microservice_1_inmueble.repository.InmuebleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InmuebleServiceImp implements InmuebleService{
    @Autowired
   private final InmuebleRepository inmuebleRepository;
    @Override
    public List<Inmueble> getAllInmuebles() {
        return inmuebleRepository.findAll();
    }
    @Override
    public Inmueble createInmueble(Inmueble inmueble) {
        inmueble.setFechaDeCreacion(LocalDateTime.now());
        return inmuebleRepository.save(inmueble);
    }

    @Override
    public void deleteInmueble(Long id) {
        // This method should delete an Inmueble object by its ID
      inmuebleRepository.deleteById(id);
    }
}
