package com.bank.banking_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.banking_api.model.Account;
import com.bank.banking_api.model.User;

public interface AccountRepository extends JpaRepository<Account, Long>{
    boolean existsByAccountNumber(String accountNumber);
    List<Account> findByUser (User user);
    
}
