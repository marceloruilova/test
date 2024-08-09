package com.eureka.movement.service;

import com.eureka.movement.mapper.MovementMapper;
import com.eureka.movement.model.Account;
import com.eureka.movement.model.Movement;
import com.eureka.movement.repository.MovementRepository;
import com.eureka.movement.request.MovementRequest;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
public class MovementService {

    @Autowired
    private MovementRepository movementRepository;

    @Autowired
    private AccountService accountService;

    private MovementMapper movementMapper;

    public Movement saveOrUpdate(Movement movement) {
        return movementRepository.save(movement);
    }

    public boolean exists(Long id) {
        return movementRepository.existsById(id);
    }

    public Movement getMovementById(Long id) {
        return movementRepository.findById(id).orElseThrow(() -> new RuntimeException("Movement not found"));
    }

    public List<Movement> getAllMovements() {
        return movementRepository.findAll();
    }

    public Movement updateAndMapMovement(Long id, Movement movement) {
        return movementRepository.findById(id)
                .map(existingMovement -> {
                    existingMovement.setDate(movement.getDate());
                    existingMovement.setMovementType(movement.getMovementType());
                    existingMovement.setValue(movement.getValue());
                    existingMovement.setBalance(movement.getBalance());
                    existingMovement.setClientId(movement.getClientId());
                    return movementRepository.save(existingMovement);
                })
                .orElseGet(() -> movementRepository.save(movement));
    }

    public void deleteMovement(Long id) {
        movementRepository.deleteById(id);
    }

    public Movement createOrUpdateMovement(MovementRequest movementRequest) throws NotFoundException {
        BigDecimal newBalance;
        Movement movement = movementMapper.toEntity(movementRequest);

        if (exists(movement.getId())) {
            newBalance = movement.getBalance().add(movement.getValue());
        } else {
            Account account = accountService.getAccountById(Long.parseLong(movement.getAccountId()));
            newBalance = account.getInitialBalance().add(movement.getValue());
        }

        if (newBalance.compareTo(BigDecimal.ZERO) < 0) {
            throw new NotFoundException("Unavailable balance");
        }

        movement.setDate(Date.from(Instant.now()));

        return saveOrUpdate(movement);
    }
}
