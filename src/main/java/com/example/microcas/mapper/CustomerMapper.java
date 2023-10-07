package com.example.microcas.mapper;

import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import com.example.microcas.domain.Customer;
import com.example.microcas.dto.CustomerDto;
import com.example.microcas.mapper.decorator.CustomerDecorator;

@Mapper(componentModel = "spring")
@DecoratedWith(CustomerDecorator.class)
public interface CustomerMapper {

    Customer toDomain(CustomerDto customerDto);
    CustomerDto toDto(Customer customer);
}
