package com.api_gateway_microservice.request;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        value = "microservice-1-inmueble",
        path = "api/v1/inmuebles", //
      //  url = "${inmueble.service.url}", // Use a property to define the URL
        configuration = FeignConfiguration.class
)
public interface InmuebleServiceRequest {

   @PostMapping
    Object createInmueble(@RequestBody Object inmuebleRequest); // Adjust the type as necessary
    @DeleteMapping("/{idInmueble}")
    void deleteInmueble(@PathVariable("idInmueble") Long idInmueble/*Long inmuebleId*/); // Adjust the type as necessary
  @GetMapping/*()*/
  List<Object> getAllInmuebles(); // Adjust the return type as necessary

}
