package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;

public interface AccountDao {


    BigDecimal getBalance(int accountId);

    // TODO: Create the account record with initial balance
    boolean createAccount(Account account);

    Account getAccountByUserId(int userId);

   BigDecimal updateBalance(int accountFrom, BigDecimal subtract);
}

