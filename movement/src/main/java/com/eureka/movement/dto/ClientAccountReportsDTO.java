package com.eureka.movement.dto;

import java.util.Date;
import java.util.List;

public class ClientAccountReportsDTO {
    private Date reportDate;
    private String cliente;
    private List<AccountReportDTO> accountReports;

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public List<AccountReportDTO> getAccountReports() {
        return accountReports;
    }

    public void setAccountReports(List<AccountReportDTO> accountReports) {
        this.accountReports = accountReports;
    }
}
