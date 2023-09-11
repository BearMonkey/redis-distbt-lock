package org.monkey.distbtlock.account.service.impl;

import org.monkey.distbtlock.account.mapper.AccountMapper;
import org.monkey.distbtlock.account.pojo.Account;
import org.monkey.distbtlock.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;
    @Override
    public void addNewAccount(Account account) {
        accountMapper.insert(account);
    }

    @Override
    public List<Account> findAllAccount() {
        return accountMapper.selectAll();
    }
}
