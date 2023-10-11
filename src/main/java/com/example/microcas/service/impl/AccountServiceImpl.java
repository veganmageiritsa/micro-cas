package com.example.microcas.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.microcas.constants.AccountConstants;
import com.example.microcas.domain.Account;
import com.example.microcas.domain.Customer;
import com.example.microcas.dto.AccountDto;
import com.example.microcas.dto.CustomerDto;
import com.example.microcas.exception.CustomerAlreadyExistsException;
import com.example.microcas.exception.ResourceNotFoundException;
import com.example.microcas.mapper.AccountMapper;
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
    
    private final AccountMapper accountMapper;
    
    
    @Override
    public List<AccountDto> findAllAccounts() {
        return accountRepository.findAll()
                                .stream()
                                .map(accountMapper::toDto)
                                .toList();
    }
    
    @Override
    public AccountDto createAccount(final CustomerDto customerDto) {
        customerRepository.findByMobileNumber(customerDto.getMobileNumber())
                          .ifPresent(customer ->{
                                         throw new CustomerAlreadyExistsException(String.format("Customer already exists for number : %s", customerDto.getMobileNumber()));
                          });
        var customer = customerMapper.toDomain(customerDto);
        customerRepository.save(customer);
        Account account = createAccount(customer.getCustomerId());
        Account createdAccount = accountRepository.save(account);
        return accountMapper.toDto(createdAccount);
        
    }
    
    @Override
    public CustomerDto fetchAccountDetails(final String mobileNumber) {
        var customer = customerRepository.findByMobileNumber(mobileNumber)
                                         .orElseThrow(() -> new ResourceNotFoundException("customer", "mobileNumber", mobileNumber));
        
        var account = accountRepository.findByCustomerId(customer.getCustomerId())
                                       .orElseThrow(() -> new ResourceNotFoundException("account", "customer", customer.toString()));
        
        CustomerDto customerDto = customerMapper.toDto(customer);
        customerDto.setAccountDto(accountMapper.toDto(account));
        return customerDto;
    }
    
    @Override
    public CustomerDto updateAccount(final CustomerDto customerDto) {
        return Optional.ofNullable(customerDto.getAccountDto())
                       .map(accountDto -> {
                           Account account = accountRepository.findById(accountDto.getAccountNumber())
                                                              .orElseThrow(() -> new ResourceNotFoundException("account", "accountNumber",
                                                                                                               accountDto.getAccountNumber().toString()));
            
                           account.setAccountType(accountDto.getAccountType());
                           account.setBranchAddress(accountDto.getBranchAddress());
                           accountRepository.save(account);
                           Customer customer = customerRepository.findById(account.getCustomerId())
                                                                 .orElseThrow(() -> new ResourceNotFoundException("customer", "customerId",
                                                                                                                  account.getCustomerId().toString()));
            
                           customer.setEmail(customerDto.getEmail());
                           customer.setMobileNumber(customerDto.getMobileNumber());
                           customer.setName(customerDto.getName());
                           customerRepository.save(customer);
                           return customerMapper.toDto(customer);
                       }).orElseGet(() -> {
                                        Customer customer = customerRepository.findByMobileNumber(customerDto.getMobileNumber())
                                                                              .orElseThrow(() -> new ResourceNotFoundException("customer", "mobileNumber",
                                                                                                                               customerDto.getMobileNumber()));
                                        customer.setEmail(customerDto.getEmail());
                                        customer.setMobileNumber(customerDto.getMobileNumber());
                                        customer.setName(customerDto.getName());
                                        customerRepository.save(customer);
                                        return customerMapper.toDto(customer);
                                    }
            
            );
        
    }
    
    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
            () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        accountRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
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
