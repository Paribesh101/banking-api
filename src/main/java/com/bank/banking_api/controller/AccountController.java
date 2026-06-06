package com.bank.banking_api.controller;

import java.math.BigDecimal;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.banking_api.model.Account;
import com.bank.banking_api.repository.UserRepository;
import com.bank.banking_api.service.AccountService;
import com.bank.banking_api.model.User;
import com.bank.banking_api.repository.AccountRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {
    
    private final AccountService accountService;
    private final UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Map<String, Long> request){
        Long userId = request.get("userId");
        User user = userRepository.findById(userId).orElseThrow(() -> new 
        RuntimeException("User not found"));
        Account created = accountService.createAccount(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<Account> deposit(@PathVariable Long id, @RequestBody Map<String, BigDecimal> request){
        BigDecimal amount = request.get("amount");

        Account updated = accountService.deposit(id, amount);
        return ResponseEntity.ok(updated);
    }

    @PostMapping("/{id}/withdraw")
    public ResponseEntity<Account> withdraw(@PathVariable Long id, @RequestBody Map<String, BigDecimal> request){
        BigDecimal amount = request.get("amount");

        Account updated = accountService.withdraw(id, amount);
        return ResponseEntity.ok(updated);
    }

    @PostMapping("transfer")
    public ResponseEntity<Void> transfer(@RequestBody Map<String, Object> request){
        Long fromAccountId = Long.valueOf(request.get("fromAccountId").toString());
        Long toAccountId = Long.valueOf(request.get("toAccountId").toString());
        BigDecimal amount = new BigDecimal(request.get("amount").toString());
        accountService.transfer(fromAccountId, toAccountId, amount);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable Long id) {
        Account account = accountService.getAccount(id);
        return ResponseEntity.ok(account);
    }
}
