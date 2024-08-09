package com.eureka.movement.controller;

import com.eureka.movement.dto.ClientAccountReportsDTO;
import com.eureka.movement.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/account-state")
    public ResponseEntity<ClientAccountReportsDTO> getAccountState(
            @RequestParam String clientId,
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") Date startDate,
            @RequestParam @DateTimeFormat(pattern = "dd/MM/yyyy") Date endDate) {

        ClientAccountReportsDTO report = reportService.generateAccountReport(clientId, startDate, endDate);
        return ResponseEntity.ok(report);
    }
}
