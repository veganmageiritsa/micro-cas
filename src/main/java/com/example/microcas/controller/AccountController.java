package com.example.microcas.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.microcas.constants.AccountConstants;
import com.example.microcas.domain.Account;
import com.example.microcas.dto.CustomerDto;
import com.example.microcas.dto.ResponseDto;
import com.example.microcas.service.AccountService;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/account", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

    private final AccountService accountService;
    @GetMapping("/all")
    public ResponseEntity<List<Account>> findAllAccounts(){
        return ResponseEntity.ok(accountService.findAllAccounts());
    }
    
    @PostMapping("/create")
    public ResponseEntity<ResponseDto<Account>> createAccount(@RequestBody CustomerDto customerDto){
    
        Account account = accountService.createAccount(customerDto);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(new ResponseDto<>(AccountConstants.STATUS_201, AccountConstants.MESSAGE_201, account));
    }
}
