package com.eureka.movement.service;
import com.eureka.movement.model.Cuenta;
import com.eureka.movement.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    public Cuenta saveOrUpdate(Cuenta cuenta) {
        return cuentaRepository.save(cuenta);
    }

    public Cuenta getCuentaById(Long id) {
        return cuentaRepository.findById(id).orElseThrow(() -> new RuntimeException("Cuenta not found"));
    }

    public List<Cuenta> getCuentas() {
        return cuentaRepository.findAll();
    }

    public Cuenta updateCuenta(Long id, Cuenta cuenta) {
        return cuentaRepository.findById(id)
                .map(existingCuenta -> {
                    existingCuenta.setNumeroCuenta(cuenta.getNumeroCuenta());
                    existingCuenta.setTipoCuenta(cuenta.getTipoCuenta());
                    existingCuenta.setSaldoInicial(cuenta.getSaldoInicial());
                    existingCuenta.setEstado(cuenta.getEstado());
                    existingCuenta.setClienteId(cuenta.getClienteId());
                    return cuentaRepository.save(existingCuenta);
                })
                .orElseGet(() -> cuentaRepository.save(cuenta));
    }

    public void deleteCuenta(Long id) {
        cuentaRepository.deleteById(id);
    }
}
