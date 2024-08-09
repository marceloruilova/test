package com.eureka.movement.repository;

import com.eureka.movement.model.Movement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface MovementRepository extends JpaRepository<Movement, Long> {

    List<Movement> findByAccountIdAndDateBetween(String id, Date startDate, Date endDate);

}
