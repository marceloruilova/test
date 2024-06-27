package com.eureka.movement.repository;

import com.eureka.movement.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CuentaRepository  extends JpaRepository<Cuenta, Long> {
    List<Cuenta> findByClienteId(String clienteId);
}
