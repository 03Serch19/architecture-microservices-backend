package com.compra_microservice.repository;

import com.compra_microservice.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompraRepository extends JpaRepository<Compra, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario
   List<Compra> findAllByUserId(Long userId);
}
