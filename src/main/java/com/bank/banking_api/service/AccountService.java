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

    public Account deposit(Long accountId, BigDecimal amount){
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        account.setBalance(account.getBalance().add(amount));
        return accountRepository.save(account);
    }

    public Account withdraw(Long accountId, BigDecimal amount){
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new RuntimeException("Account not found"));
        if (account.getBalance().compareTo(amount) < 0){
            throw new RuntimeException("Insufficient funds");
        }
        account.setBalance(account.getBalance().subtract(amount));
        return accountRepository.save(account);
    }

    public void transfer(Long fromAccountId, Long toAccountId, BigDecimal amount){
        Account fromAccount = accountRepository.findById(fromAccountId).orElseThrow(() -> new RuntimeException("Account not found"));
        Account toAccount = accountRepository.findById(toAccountId).orElseThrow(() -> new RuntimeException("Account not found"));
        if (fromAccount.getBalance().compareTo(amount) < 0){
            throw new RuntimeException("Insufficient funds");
        }
        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }

    public Account getAccount(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }


}
