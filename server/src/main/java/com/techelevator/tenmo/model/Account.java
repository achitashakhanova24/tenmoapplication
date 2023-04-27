package com.techelevator.tenmo.model;

import java.math.BigDecimal;


public class Account {

    private int accountID;
    private boolean accountActivated;
    private BigDecimal accountBalance;

    public Account() { }

    public Account(int accountID, BigDecimal accountBalance) {
        this.accountID = accountID;
        this.accountActivated = true;
        this.accountBalance = accountBalance;
    }

    public int getAccountID() {
        return accountID;
    }

    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
    }

    public int getId() {
        return accountID;
    }

    public void setId(int id) {
        this.accountID = accountID;
    }

    public boolean isAccountActivated() {
        return accountActivated;
    }

    public void setAccountActivated(boolean accountActivated) {
        this.accountActivated = accountActivated;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountID=" + accountID +
                ", accountActivated=" + accountActivated +
                '}';
    }
}

