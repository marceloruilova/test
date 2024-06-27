package com.eureka.movement.service;

import com.eureka.movement.model.Movimiento;
import com.eureka.movement.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    public Movimiento saveOrUpdate(Movimiento movimiento) {
        return movimientoRepository.save(movimiento);
    }

    public boolean exists(Long id) {
        return movimientoRepository.existsById(id);
    }

    public Movimiento getMovimientoById(Long id) {
        return movimientoRepository.findById(id).orElseThrow(() -> new RuntimeException("Movimiento not found"));
    }

    public List<Movimiento> getAllMovimientos() {
        return movimientoRepository.findAll();
    }

    public Movimiento updateMovimiento(Long id, Movimiento movimiento) {
        return movimientoRepository.findById(id)
                .map(existingMovimiento -> {
                    existingMovimiento.setFecha(movimiento.getFecha());
                    existingMovimiento.setTipoMovimiento(movimiento.getTipoMovimiento());
                    existingMovimiento.setValor(movimiento.getValor());
                    existingMovimiento.setSaldo(movimiento.getSaldo());
                    existingMovimiento.setClienteId(movimiento.getClienteId());
                    return movimientoRepository.save(existingMovimiento);
                })
                .orElseGet(() -> movimientoRepository.save(movimiento));
    }

    public void deleteMovimiento(Long id) {
        movimientoRepository.deleteById(id);
    }
}
