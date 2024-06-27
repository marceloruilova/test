package com.eureka.movement.controller;

import com.eureka.movement.model.Cuenta;
import com.eureka.movement.model.Movimiento;
import com.eureka.movement.service.CuentaService;
import com.eureka.movement.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @Autowired
    private CuentaService cuentaService;

    @GetMapping
    public ResponseEntity<?> getMovimientos() {
        List<Movimiento> savedMovimiento = movimientoService.getAllMovimientos();
        return ResponseEntity.ok(savedMovimiento);
    }

    @PostMapping
    public ResponseEntity<Movimiento> createMovimiento(@RequestBody Movimiento movimiento) {
        try {
            double newSaldo;
            if (movimientoService.exists(movimiento.getId())) {
                newSaldo = movimiento.getSaldo() + movimiento.getValor();
            } else {
                Cuenta cuenta = cuentaService.getCuentaById(Long.parseLong(movimiento.getCuentaId()));
                newSaldo = cuenta.getSaldoInicial() + movimiento.getValor();
            }

            if (newSaldo < 0) {
                throw new Exception("Saldo no disponible");
            }

            movimiento.setFecha(Date.from(Instant.now()));

            Movimiento newMovimiento = movimientoService.saveOrUpdate(movimiento);
            return ResponseEntity.ok(newMovimiento);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
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
