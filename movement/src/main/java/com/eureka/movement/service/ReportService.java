package com.eureka.movement.service;

import com.eureka.movement.dto.AccountReportDTO;
import com.eureka.movement.dto.ClientAccountReportsDTO;
import com.eureka.movement.model.Cuenta;
import com.eureka.movement.model.Movimiento;
import com.eureka.movement.repository.CuentaRepository;
import com.eureka.movement.repository.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private MovimientoRepository movimientoRepository;

    public ClientAccountReportsDTO generateAccountReport(String clienteId, Date startDate, Date endDate) {
        List<Cuenta> cuentas = cuentaRepository.findByClienteId(clienteId);
        List<AccountReportDTO> accountReports = new ArrayList<>();

        for (Cuenta cuenta : cuentas) {
            List<Movimiento> movimientos = movimientoRepository.findByCuentaIdAndFechaBetween(cuenta.getId().toString(), startDate, endDate);
            double totalMovimientos = movimientos.stream().mapToDouble(Movimiento::getValor).sum();

            AccountReportDTO accountReport = new AccountReportDTO();
            accountReport.setNumeroCuenta(cuenta.getNumeroCuenta());
            accountReport.setTipo(cuenta.getTipoCuenta());
            accountReport.setSaldoInicial(cuenta.getSaldoInicial());
            accountReport.setEstado(cuenta.getEstado());
            accountReport.setMovimiento(totalMovimientos);
            accountReport.setSaldoDisponible(cuenta.getSaldoInicial()-totalMovimientos);

            accountReports.add(accountReport);
        }

        ClientAccountReportsDTO clientReport = new ClientAccountReportsDTO();
        clientReport.setReportDate(new Date());
        clientReport.setCliente(cuentas.isEmpty() ? "Unknown" : cuentas.get(0).getClienteId());
        clientReport.setAccountReports(accountReports);

        return clientReport;
    }
}
