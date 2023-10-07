package com.example.microcas.controller;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.microcas.domain.Account;
import com.example.microcas.domain.Customer;
import com.example.microcas.service.CustomerService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/customer")
public class CustomerController {
    
    private final CustomerService customerService;
    @GetMapping("/all")
    public ResponseEntity<List<Customer>> findAllCustomer(){
        return ResponseEntity.ok(customerService.findAllCustomers());
    }
}
