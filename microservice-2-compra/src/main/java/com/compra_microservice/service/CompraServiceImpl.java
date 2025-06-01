package com.compra_microservice.service;

import com.compra_microservice.model.Compra;
import com.compra_microservice.repository.CompraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompraServiceImpl implements CompraService {

    @Autowired
    private final CompraRepository compraRepository;

    @Override
    public Compra saveCompra(Compra compra) {
        compra.setFechaCompra(LocalDateTime.now());
        return compraRepository.save(compra);//recordarque el objeto devuelto traera el id el cvual es el cmpo que al bd generara
    }
    @Override
    public List<Compra> findAllComprasOfUser(Long userId) {
        return compraRepository.findAllByUserId(userId);
    }

}
