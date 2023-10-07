package com.example.microcas.service;

import java.util.List;

import com.example.microcas.domain.Account;
import com.example.microcas.dto.CustomerDto;

public interface AccountService {
    List<Account> findAllAccounts();
    
    Account createAccount(CustomerDto customerDto);
    
}
