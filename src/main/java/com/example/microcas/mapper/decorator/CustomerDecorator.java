//package com.example.microcas.mapper.decorator;
//
//import java.util.UUID;
//
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.example.microcas.domain.Customer;
//import com.example.microcas.dto.CustomerDto;
//import com.example.microcas.mapper.CustomerMapper;
//
//
//public abstract class CustomerDecorator implements CustomerMapper {
//
//    @Autowired
//    @Qualifier("delegate")
//    private CustomerMapper delegate;
//
//    @Override
//    public Customer toDomain(final CustomerDto customerDto) {
//        Customer customer = delegate.toDomain(customerDto);
//        customer.setCustomerId(UUID.randomUUID());
//        return customer;
//    }
//
//}
