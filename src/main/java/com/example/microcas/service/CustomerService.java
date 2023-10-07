package com.example.microcas.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.microcas.domain.Customer;


public interface CustomerService {
    
    List<Customer> findAllCustomers();
    
}
