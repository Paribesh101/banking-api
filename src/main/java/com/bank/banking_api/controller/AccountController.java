package com.bank.banking_api.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.banking_api.model.Account;
import com.bank.banking_api.repository.UserRepository;
import com.bank.banking_api.service.AccountService;
import com.bank.banking_api.model.User;
import lombok.RequiredArgsConstructor;

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

}
