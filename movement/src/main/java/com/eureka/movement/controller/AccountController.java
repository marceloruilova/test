package com.eureka.movement.controller;
import com.eureka.movement.model.Account;
import com.eureka.movement.request.AccountRequest;
import com.eureka.movement.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public ResponseEntity<List<Account>> getAccounts() {
        List<Account> savedAccount = accountService.getAccount();
        return ResponseEntity.ok(savedAccount);
    }

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody AccountRequest accountRequest) {
        Account newAccount = accountService.saveOrUpdate(accountRequest);
        return ResponseEntity.ok(newAccount);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        Account account = accountService.getAccountById(id);
        return ResponseEntity.ok(account);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody Account account) {
        Account updatedAccount = accountService.updateAndMapAccount(id, account);
        return ResponseEntity.ok(updatedAccount);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok().build();
    }
}
