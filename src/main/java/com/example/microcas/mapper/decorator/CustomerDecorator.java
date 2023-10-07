package com.example.microcas.mapper.decorator;

import java.util.UUID;

import com.example.microcas.domain.Customer;
import com.example.microcas.dto.CustomerDto;
import com.example.microcas.mapper.CustomerMapper;

public abstract class CustomerDecorator implements CustomerMapper {
    
    
    private CustomerMapper customerMapper;
    
    @Override
    public Customer toDomain(final CustomerDto customerDto) {
        Customer customer = customerMapper.toDomain(customerDto);
        customer.setCustomerId(UUID.randomUUID());
        return customer;
    }
    
    public void setCustomerMapper(final CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }
    
}
