package com.example.microcas.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.microcas.constants.AccountConstants;
import com.example.microcas.domain.Account;
import com.example.microcas.dto.CustomerDto;
import com.example.microcas.exception.CustomerAlreadyExistsException;
import com.example.microcas.mapper.CustomerMapper;
import com.example.microcas.repository.AccountRepository;
import com.example.microcas.repository.CustomerRepository;
import com.example.microcas.service.AccountService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    
    private final AccountRepository accountRepository;
    
    private final CustomerRepository customerRepository;
    
    private final CustomerMapper customerMapper;
    
    
    @Override
    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }
    
    @Override
    public Account createAccount(final CustomerDto customerDto) {
        customerRepository.findByMobileNumber(customerDto.getMobileNumber())
            .map(customer ->
                     new CustomerAlreadyExistsException(String.format("Customer already exists for number : %s", customerDto.getMobileNumber()))
            );
        var customer = customerMapper.toDomain(customerDto);
        customerRepository.save(customer);
        Account account = createAccount(customer.getCustomerId());
        return accountRepository.save(account);
       
    }
    
    private Account createAccount(final UUID customerId) {
    
        var account = new Account();
        account.setAccountNumber(UUID.randomUUID());
        account.setAccountType(AccountConstants.SAVINGS);
        account.setBranchAddress(AccountConstants.ADDRESS);
        account.setCustomerId(customerId);
        return account;
    }
    
}
