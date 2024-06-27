package com.eureka.movement.controller;

import com.eureka.movement.dto.AccountReportDTO;
import com.eureka.movement.dto.ClientAccountReportsDTO;
import com.eureka.movement.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/estado-cuenta")
    public ResponseEntity<ClientAccountReportsDTO> getEstadoCuenta(
            @RequestParam String clienteId,
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") Date endDate) {

        ClientAccountReportsDTO report = reportService.generateAccountReport(clienteId, startDate, endDate);
        return ResponseEntity.ok(report);
    }
}
