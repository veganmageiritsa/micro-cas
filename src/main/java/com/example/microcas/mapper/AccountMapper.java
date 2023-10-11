package com.example.microcas.mapper;

import java.util.UUID;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.microcas.domain.Account;
import com.example.microcas.dto.AccountDto;

@Mapper(componentModel = "spring")
//@DecoratedWith(AccountDecorator.class)
public interface AccountMapper {
      AccountDto toDto(Account account);
      
      Account toDomain(AccountDto accountDto);
      
      @Mapping(target = "customerId", ignore = true)
      @Mapping(target = "accountNumber", ignore = true)
      Account updateAccountDetails(AccountDto accountDto);
      
      @AfterMapping
      default void setId(@MappingTarget Account account){
            if(account.getAccountNumber() == null) {
                  account.setAccountNumber(UUID.randomUUID());
            }
      }
}
