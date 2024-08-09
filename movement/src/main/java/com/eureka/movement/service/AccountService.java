package com.eureka.movement.service;

import com.eureka.movement.mapper.AccountMapper;
import com.eureka.movement.model.Account;
import com.eureka.movement.repository.AccountRepository;
import com.eureka.movement.request.AccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    private AccountMapper accountMapper;

    public Account saveOrUpdate(AccountRequest accountRequest) {
        return accountRepository.save(accountMapper.toEntity(accountRequest));
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
    }

    public List<Account> getAccount() {
        return accountRepository.findAll();
    }

    public Account updateAndMapAccount(Long id, Account account) {
        return accountRepository.findById(id)
                .map(existingAccount -> {
                    existingAccount.setAccountNumber(account.getAccountNumber());
                    existingAccount.setAccountType(account.getAccountType());
                    existingAccount.setInitialBalance(account.getInitialBalance());
                    existingAccount.setState(account.getState());
                    existingAccount.setClientId(account.getClientId());
                    return accountRepository.save(existingAccount);
                })
                .orElseGet(() -> accountRepository.save(account));
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
}
