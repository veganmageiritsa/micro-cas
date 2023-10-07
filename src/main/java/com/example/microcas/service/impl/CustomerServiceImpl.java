package com.example.microcas.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.microcas.domain.Customer;
import com.example.microcas.repository.CustomerRepository;
import com.example.microcas.service.CustomerService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
   @Override
    public List<Customer> findAllCustomers(){
        return customerRepository.findAll();
    }
}
