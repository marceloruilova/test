package com.eureka.movement.repository;

import com.eureka.movement.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovimientoRepository  extends JpaRepository<Movimiento, Long> {
}
