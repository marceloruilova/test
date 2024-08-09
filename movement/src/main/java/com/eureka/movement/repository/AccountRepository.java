package com.eureka.movement.repository;

import com.eureka.movement.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByClienteId(String clienteId);
}
