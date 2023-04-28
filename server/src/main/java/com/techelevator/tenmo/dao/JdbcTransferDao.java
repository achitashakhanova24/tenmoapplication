package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.exceptions.InsufficientFundsException;
import com.techelevator.tenmo.exceptions.TransferException;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

    @Component
    public class JdbcTransferDao implements TransferDao{
    private final AccountDao accountDao;
    private JdbcTemplate jdbcTemplate;
    public JdbcTransferDao(JdbcTemplate jdbcTemplate, AccountDao accountDao){
        this.jdbcTemplate = jdbcTemplate;
        this.accountDao = accountDao;
    }

    @Override
         public void sendTransfer(Transfer transfer) {

        if (transfer.getMoneyTransfer().doubleValue() <= 0) {
            throw new IllegalArgumentException("Transfer amount must be > 0");
        }

        if (transfer.getAccountFrom() == transfer.getAccountTo()) {
            throw new IllegalArgumentException("You cannot send money to yourself.");
        }
            try {
                BigDecimal senderBalance = accountDao.getBalance((transfer.getAccountFrom()));
                BigDecimal transferAmount = transfer.getMoneyTransfer();
               if (senderBalance.compareTo(transferAmount) < 0 ) {
                    try {
                        throw new InsufficientFundsException(HttpStatus.BAD_REQUEST, "Sorry, you do not have enough funds to complete this transfer.");
                    } catch (InsufficientFundsException e) {
                        System.out.println(e.getMessage());
                    }
                }

               accountDao.updateBalance(transfer.getAccountFrom(), senderBalance.subtract(transferAmount));
                BigDecimal receiverBalance = accountDao.getBalance(transfer.getAccountTo());
              accountDao.updateBalance(transfer.getAccountTo(), receiverBalance.add(transferAmount));
            } catch (DataAccessException e) {
                try {
                    throw new TransferException(HttpStatus.BAD_REQUEST, "Oops. An error occurred while processing the transfer.");
                } catch (TransferException ex) {
                    System.out.println(ex.getMessage());
                }
                transfer.setTransferStatus("Approved");
                sendTransfer(transfer);
            }

        }
    }