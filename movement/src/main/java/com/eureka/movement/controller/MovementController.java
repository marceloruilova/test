package com.eureka.movement.controller;

import com.eureka.movement.api.ClientServiceClient;
import com.eureka.movement.model.Movement;
import com.eureka.movement.service.MovementService;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movements")
public class MovementController {

    @Autowired
    private MovementService movementService;

    @Autowired
    private ClientServiceClient clientServiceClient;


    @GetMapping
    public ResponseEntity<List<Movement>> getMovements() {
        List<Movement> savedMovement = movementService.getAllMovements();
        return ResponseEntity.ok(savedMovement);
    }

    @PostMapping
    public ResponseEntity<Movement> createMovement(@RequestBody Movement movement) {
        try {
            Movement newMovement = movementService.createOrUpdateMovement(movement);
            return ResponseEntity.ok(newMovement);
        } catch (NotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movement> getMovementById(@PathVariable Long id) {
        Movement movement = movementService.getMovementById(id);
        return ResponseEntity.ok(movement);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movement> updateMovement(@PathVariable Long id, @RequestBody Movement movement) {
        Movement updatedMovement = movementService.updateAndMapMovement(id, movement);
        return ResponseEntity.ok(updatedMovement);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovement(@PathVariable Long id) {
        movementService.deleteMovement(id);
        return ResponseEntity.ok().build();
    }
}
