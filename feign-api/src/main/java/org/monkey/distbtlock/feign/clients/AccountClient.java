package org.monkey.distbtlock.feign.clients;

import org.monkey.distbtlock.feign.pojo.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient("accountClient")
public interface AccountClient {
    @PostMapping
    public String addNewAccount(Account account);

    @GetMapping
    public List<Account> findAll();
}
