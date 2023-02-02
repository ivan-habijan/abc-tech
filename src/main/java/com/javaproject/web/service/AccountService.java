package com.javaproject.web.service;

import com.javaproject.web.entity.Account;

import java.util.List;

public interface AccountService {
    List<Account> getAllAccounts();
    Account saveAccount(Account account);

    Account editAccount(Account account);

    Account getAccountById(Long id);

    void deleteAccount(Long id);
}
