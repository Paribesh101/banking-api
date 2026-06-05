package com.bank.banking_api.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.bank.banking_api.model.Account;
import com.bank.banking_api.model.User;
import com.bank.banking_api.repository.AccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public Account createAccount(User user){
        Account account = new Account();
        account.setUser(user); // link the account to the user
        account.setBalance(BigDecimal.ZERO); // starts with zero balance
        account.setAccountNumber(String.valueOf(System.currentTimeMillis()));
        return accountRepository.save(account);
    }

}
