package com.example.microcas.dto;

import java.util.UUID;


import lombok.Data;

@Data
public class AccountDto {
    private UUID accountNumber;
    private String accountType;
    
    private String branchAddress;
}
