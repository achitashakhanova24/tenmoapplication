package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;

public interface AccountDao {

    void updateAccountFromBalance(int accountFrom, BigDecimal subtract);
    void updateAccountToBalance(int accountTo, BigDecimal add);
    BigDecimal getBalance(int accountId);
    boolean createAccount(Account account);
    Account getAccountByUserId(int userId);
}

