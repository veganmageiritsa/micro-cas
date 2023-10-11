//package com.example.microcas.mapper.decorator;
//
//import java.util.UUID;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.example.microcas.domain.Account;
//import com.example.microcas.dto.AccountDto;
//import com.example.microcas.mapper.AccountMapper;
//
//
//public abstract class AccountDecorator implements AccountMapper {
//
//    @Autowired
//    @Qualifier("delegate")
//    private AccountMapper delegate;
//
//
//    @Override
//    public Account toDomain(final AccountDto accountDto) {
//        Account account = delegate.toDomain(accountDto);
//        account.setAccountNumber(UUID.randomUUID());
//        return account;
//    }
//
//}
