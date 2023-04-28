package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfer {
    private int transferId;
    private int accountFrom;
    private int accountTo;
    private BigDecimal moneyTransfer;
    private String transferStatus;
    private BigDecimal transferBalance;
    // TODO: Status / Type instance variables "Approved" / "Pending"
    // Send / Request


    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }

    public int getAccountFrom() {
        return accountFrom;
    }

    public void setAccountFrom(int accountFrom) {
        this.accountFrom = accountFrom;
    }

    public int getAccountTo() {
        return accountTo;
    }

    public void setAccountTo(int accountTo) {
        this.accountTo = accountTo;
    }

    public BigDecimal getMoneyTransfer() {
        return moneyTransfer;
    }

    public void setMoneyTransfer(BigDecimal moneyTransfer) {
        this.moneyTransfer = moneyTransfer;
    }

    public String getTransferStatus() {
        return transferStatus;
    }

    public void  setTransferStatus(String transferStatus) {
        this.transferStatus = transferStatus;
    }

    public BigDecimal getTransferBalance() {
        return transferBalance;
    }

    public void setTransferBalance(BigDecimal transferBalance) {
        this.transferBalance = transferBalance;
    }

}