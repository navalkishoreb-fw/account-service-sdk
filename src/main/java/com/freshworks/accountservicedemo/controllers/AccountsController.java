package com.freshworks.accountservicedemo.controllers;

import com.freshworks.accountservicedemo.dto.AccountResponse;
import com.freshworks.accountservicedemo.services.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AccountsController {

    private final AccountService accountService;

    public AccountsController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/accounts/{id}")
    public ResponseEntity<AccountResponse> getAccounts(@PathVariable String id) {
        return ResponseEntity.ok(accountService.getAccount(id));
    }

}
