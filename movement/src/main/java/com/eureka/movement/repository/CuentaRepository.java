package com.eureka.movement.repository;

import com.eureka.movement.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepository  extends JpaRepository<Cuenta, Long> {
}
