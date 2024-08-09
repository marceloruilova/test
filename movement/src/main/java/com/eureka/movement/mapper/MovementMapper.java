package com.eureka.movement.mapper;

import com.eureka.movement.model.Movement;
import com.eureka.movement.request.MovementRequest;

public class MovementMapper {

    public Movement toEntity(MovementRequest movementRequest) {
        Movement movement = new Movement();
        movement.setId(movementRequest.getId() != null ? movementRequest.getId() : null);
        movement.setClientId(movementRequest.getClientId() != null ? movementRequest.getClientId() : null);
        movement.setAccountId(movementRequest.getAccountId());
        movement.setDate(movementRequest.getDate());
        movement.setMovementType(movementRequest.getMovementType());
        movement.setValue(movementRequest.getValue());
        movement.setBalance(movementRequest.getBalance());
        return movement;
    }

}
