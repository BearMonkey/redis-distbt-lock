package org.monkey.distbtlock.account.controller;

import org.monkey.distbtlock.account.pojo.Account;
import org.monkey.distbtlock.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountCotroller {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public String addNewSource(Account account) {
        accountService.addNewAccount(account);
        return "add new account success";
    }

    @GetMapping
    public List<Account> findAll() {
        return accountService.findAllAccount();
    }

}
