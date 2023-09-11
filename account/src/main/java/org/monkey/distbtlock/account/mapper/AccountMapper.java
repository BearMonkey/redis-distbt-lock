package org.monkey.distbtlock.account.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.monkey.distbtlock.account.pojo.Account;

import java.util.List;

@Mapper
public interface AccountMapper {
    void insert(Account account);

    List<Account> selectAll();
}
