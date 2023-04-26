package com.techelevator.tenmo.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Account {

    private int accountID;
    private String accountPassword;
    private boolean accountActivated;
    private BigDecimal accountBalance;
    private Set<Authority> authorities = new HashSet<>();

    public Account() { }

    public Account(int accountID, String accountPassword, String authorities, BigDecimal accountBalance) {
        this.accountID = accountID;
        this.accountPassword = accountPassword;
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


    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public boolean isAccountActivated() {
        return accountActivated;
    }

    public void setAccountActivated(boolean accountActivated) {
        this.accountActivated = accountActivated;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public void setAuthorities(String authorities) {
        String[] roles = authorities.split(",");
        for(String role : roles) {
            this.authorities.add(new Authority("ROLE_" + role));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountID== account.accountID &&
               accountActivated == account.accountActivated &&
                Objects.equals(accountPassword, account.accountPassword) &&
                Objects.equals(authorities, account.authorities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountID, accountPassword, accountActivated, authorities);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountID=" + accountID +
                ", accountActivated=" + accountActivated +
                ", authorities=" + authorities +
                '}';
    }
}

