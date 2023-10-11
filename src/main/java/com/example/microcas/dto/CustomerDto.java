package com.example.microcas.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class CustomerDto extends BaseDto {
    
    @NotEmpty(message = " name must be present")
    private String name;
    
    @NotEmpty(message = " email must be present")
    
    private String email;
    
    @NotEmpty(message = " mobile number is required")
    @Pattern(regexp = "^\\d{10}$\n", message = "please provide 10 digits")
    private String mobileNumber;
    
    private AccountDto accountDto;
    
}
