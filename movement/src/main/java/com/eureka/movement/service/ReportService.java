package com.eureka.movement.service;

import com.eureka.movement.dto.AccountReportDTO;
import com.eureka.movement.dto.ClientAccountReportsDTO;
import com.eureka.movement.model.Account;
import com.eureka.movement.model.Movement;
import com.eureka.movement.repository.AccountRepository;
import com.eureka.movement.repository.MovementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private MovementRepository movementRepository;

    public ClientAccountReportsDTO generateAccountReport(String clientId, Date startDate, Date endDate) {
        List<Account> accounts = accountRepository.findByClienteId(clientId);

        List<AccountReportDTO> accountReports = accounts.stream()
                .map(account -> generateAccountReportDTO(account, startDate, endDate))
                .toList();

        return buildClientAccountReportsDTO(accounts, accountReports);
    }

    private AccountReportDTO generateAccountReportDTO(Account account, Date startDate, Date endDate) {
        List<Movement> movements = movementRepository.findByAccountIdAndDateBetween(
                account.getId().toString(), startDate, endDate);
        BigDecimal totalMovements = calculateTotalMovements(movements);

        return buildAccountReportDTO(account, totalMovements);
    }

    private BigDecimal calculateTotalMovements(List<Movement> movements) {
        return movements.stream()
                .map(Movement::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private AccountReportDTO buildAccountReportDTO(Account account, BigDecimal totalMovements) {
        AccountReportDTO accountReport = new AccountReportDTO();
        accountReport.setAccountNumber(account.getAccountNumber());
        accountReport.setType(account.getAccountType());
        accountReport.setInitialBalance(account.getInitialBalance());
        accountReport.setState(account.getState());
        accountReport.setMovement(totalMovements);
        accountReport.setAvailableBalance(account.getInitialBalance().subtract(totalMovements));

        return accountReport;
    }

    private ClientAccountReportsDTO buildClientAccountReportsDTO(List<Account> accounts, List<AccountReportDTO> accountReports) {
        ClientAccountReportsDTO clientReport = new ClientAccountReportsDTO();
        clientReport.setReportDate(new Date());
        clientReport.setClient(accounts.isEmpty() ? "Unknown" : accounts.getFirst().getClientId());
        clientReport.setAccountReports(accountReports);
        return clientReport;
    }

}
