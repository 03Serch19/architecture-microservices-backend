package com.api_gateway_microservice.request;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "microservice-2-compra", // Name of the microservice
        //url = "${compras.service.url}",
        path = "api/v1/compras",
        configuration = FeignConfiguration.class)
public interface ComprasServiceRequest {
    @GetMapping("/{userId}")
    List<Object> getAllComprasOfUserGate(@PathVariable Long userId);

    @PostMapping
    Object createCompraGate(@RequestBody Object compra);

}
