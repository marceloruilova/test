package com.eureka.movement.api;

import com.eureka.movement.dto.ClienteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "person")
public interface ClientServiceClient {
    @GetMapping("/clientes/{id}")
    ClienteDto getClientById(@PathVariable("id") Long id);
}