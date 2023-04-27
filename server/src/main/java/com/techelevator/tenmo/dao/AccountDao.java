package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;

import java.math.BigDecimal;

public interface AccountDao {

    boolean createAccount(Account account);

    BigDecimal getBalance(int userID) throws DaoException;

    //method to decrease balance

    //method to increase balance

}

