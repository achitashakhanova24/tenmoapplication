package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;

public interface AccountDao {

    boolean createAccount(Account account);

    BigDecimal getBalance(int userID) throws DaoException;

    Account getAccountByUserId(int userId);


    //method to decrease balance

    //method to increase balance

}

