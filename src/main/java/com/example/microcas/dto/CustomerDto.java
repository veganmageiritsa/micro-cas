package com.example.microcas.dto;

import org.springframework.data.cassandra.core.mapping.Column;

import lombok.Data;

@Data
public class CustomerDto {
    
    private String name;
    
    private String email;
    
    private String mobileNumber;
    
}
