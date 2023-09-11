package org.monkey.distbtlock.account.service;

import org.monkey.distbtlock.account.pojo.Account;

import java.util.List;

public interface AccountService {
    void addNewAccount(Account account);

    List<Account> findAllAccount();
}
