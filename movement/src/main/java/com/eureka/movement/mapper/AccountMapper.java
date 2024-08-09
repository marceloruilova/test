package com.eureka.movement.mapper;

import com.eureka.movement.model.Account;
import com.eureka.movement.request.AccountRequest;

public class AccountMapper {

    public Account toEntity(AccountRequest accountRequest) {
        Account account = new Account();
        account.setId(accountRequest.getId());
        account.setClientId(accountRequest.getClientId());
        account.setAccountNumber(accountRequest.getAccountNumber());
        account.setAccountType(accountRequest.getAccountType());
        account.setInitialBalance(accountRequest.getInitialBalance());
        account.setState(accountRequest.getState());
        return account;
    }

}
