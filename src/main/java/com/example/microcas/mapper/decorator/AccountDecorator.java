package com.example.microcas.mapper.decorator;

import java.util.UUID;

import com.example.microcas.domain.Account;
import com.example.microcas.dto.AccountDto;
import com.example.microcas.mapper.AccountMapper;


public abstract class AccountDecorator implements AccountMapper {
    
    private AccountMapper accountMapper;
    
    
    @Override
    public Account toDomain(final AccountDto accountDto) {
        Account account = accountMapper.toDomain(accountDto);
        account.setAccountNumber(UUID.randomUUID());
        return account;
    }
    public void setAccountMapper(final AccountMapper accountMapper) {
        this.accountMapper = accountMapper;
    }
}
