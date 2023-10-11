package com.example.microcas.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.microcas.constants.AccountConstants;
import com.example.microcas.dto.AccountDto;
import com.example.microcas.dto.CustomerDto;
import com.example.microcas.dto.ResponseDto;
import com.example.microcas.service.AccountService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/account", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class AccountController {
    
    private final AccountService accountService;
    
    @GetMapping("/all")
    public ResponseEntity<List<AccountDto>> findAllAccounts() {
        return ResponseEntity.ok(accountService.findAllAccounts());
    }
    
    @PostMapping("/create")
    public ResponseEntity<ResponseDto<AccountDto>> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        
        var account = accountService.createAccount(customerDto);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(new ResponseDto<>(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201, account));
    }
    
    @GetMapping("/fetch")
    public ResponseEntity<ResponseDto<CustomerDto>> fetchAccountDetails(
        @RequestParam
       @Valid @Pattern(regexp = "^\\d{10}$\n", message = "please provide 10 digits")
        String mobileNumber) {
        CustomerDto customerDto = accountService.fetchAccountDetails(mobileNumber);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(new ResponseDto<>(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200, customerDto));
    }
    
    @PutMapping("/update")
    public ResponseEntity<ResponseDto<CustomerDto>> updateAccount(@Valid @RequestBody CustomerDto customerDto) {
        
        CustomerDto customer = accountService.updateAccount(customerDto);
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(new ResponseDto<>(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200, customer));
    }
    
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam
                                                            @Pattern(regexp="^\\d{10}$\n",message = "Mobile number must be 10 digits")
                                                            String mobileNumber) {
        boolean isDeleted = accountService.deleteAccount(mobileNumber);
        if(isDeleted) {
            return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(AccountConstants.STATUS_200, AccountConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                .status(HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(AccountConstants.STATUS_417, AccountConstants.MESSAGE_417_DELETE));
        }
    }
    
}
