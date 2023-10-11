package com.example.microcas.mapper;

import java.util.UUID;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.example.microcas.domain.Customer;
import com.example.microcas.dto.CustomerDto;

@Mapper(componentModel = "spring")
//@DecoratedWith(CustomerDecorator.class)
public interface CustomerMapper {
    
    Customer toDomain(CustomerDto customerDto);
    
    @Mapping(target = "accountDto", ignore = true)
    CustomerDto toDto(Customer customer);
    
    @AfterMapping
    default void setId(@MappingTarget Customer customer) {
        if(customer.getCustomerId()==null) {
            customer.setCustomerId(UUID.randomUUID());
        }
    }
    
    @Mapping(target = "customerId", ignore = true)
    Customer updateCustomerDetails(CustomerDto customerDto);
    
}
