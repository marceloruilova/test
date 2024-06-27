package com.eureka.movement.controller;

import com.eureka.movement.model.Movimiento;
import com.eureka.movement.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @PostMapping
    public ResponseEntity<Movimiento> createMovimiento(@RequestBody Movimiento movimiento) {
        Movimiento newMovimiento = movimientoService.createMovimiento(movimiento);
        return ResponseEntity.ok(newMovimiento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movimiento> getMovimientoById(@PathVariable Long id) {
        Movimiento movimiento = movimientoService.getMovimientoById(id);
        return ResponseEntity.ok(movimiento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movimiento> updateMovimiento(@PathVariable Long id, @RequestBody Movimiento movimiento) {
        Movimiento updatedMovimiento = movimientoService.updateMovimiento(id, movimiento);
        return ResponseEntity.ok(updatedMovimiento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMovimiento(@PathVariable Long id) {
        movimientoService.deleteMovimiento(id);
        return ResponseEntity.ok().build();
    }
}
