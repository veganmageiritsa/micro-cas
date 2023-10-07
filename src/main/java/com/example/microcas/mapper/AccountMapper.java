package com.example.microcas.mapper;

import java.util.UUID;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import com.example.microcas.domain.Account;
import com.example.microcas.dto.AccountDto;
import com.example.microcas.mapper.decorator.AccountDecorator;

@Mapper
@DecoratedWith(AccountDecorator.class)
public interface AccountMapper {
      AccountDto toDto(Account account);
      
      Account toDomain(AccountDto accountDto);
}
