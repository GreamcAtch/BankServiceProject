package com.bankapp.app.service;

import com.bankapp.app.controller.dto.AccountDTO;
import com.bankapp.app.entity.Account;
import com.bankapp.app.entity.Transaction;

import java.util.List;
import java.util.UUID;

public interface AccountService {
    Account getAccountById(String id);

    AccountDTO getAccountDTO(String id);

    List<AccountDTO> findAll();

    Account getById(String id);

    Account save(Account account);

    Account findAccountById(UUID accountId);

    Account updateAccount(UUID id, AccountDTO accountDTO);

    boolean deleteAccount(UUID id);

    void updateBalance(Transaction transaction);
}

