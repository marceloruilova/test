package com.eureka.movement.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "person")
public interface ClientServiceClient {

    @GetMapping("/clients/{id}")
    ClientDto getClientById(@PathVariable("id") Long id);

}