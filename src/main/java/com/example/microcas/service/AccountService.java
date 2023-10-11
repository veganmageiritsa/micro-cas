package com.example.microcas.service;

import java.util.List;

import com.example.microcas.dto.AccountDto;
import com.example.microcas.dto.CustomerDto;

public interface AccountService {
    
    List<AccountDto> findAllAccounts();
    
    AccountDto createAccount(CustomerDto customerDto);
    
    CustomerDto fetchAccountDetails(final String mobileNumber);
    
    CustomerDto updateAccount(CustomerDto customerDto);
    
    boolean deleteAccount(String mobileNumber);
    
}
