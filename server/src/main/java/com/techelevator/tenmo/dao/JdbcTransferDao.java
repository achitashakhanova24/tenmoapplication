//package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.exceptions.InsufficientFundsException;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;

//public class JdbcTransferDao implements TransferDao {
//
//    private JdbcTemplate jdbcTemplate;
//
//    public JdbcTransferDao(JdbcTemplate jdbcTemplate, AccountDao accountDao) {
//        this.jdbcTemplate = jdbcTemplate;
//        this.accountDao = accountDao;
//    }

//}
//    @Override
//    public void sendTransfer(Transfer transfer) throws InsufficientFundsException, IllegalArgumentException {
//      if (transfer.getBalance().doubleValue() <= 0) {
//          throw new IllegalArgumentException("Transfer amount must be > 0");
//       }
//      if (transfer.getAccountFromUsername().getUsername().equals(transfer.getRecipient().getUsername())) {
//        throw new IllegalArgumentException("You cannot send money to yourself.");
//      }
//      BigDecimal senderBalance = accountDao.getBalance(transfer.getAccountFromUsername());
//      if (senderBalance.compareTo(transfer.getBalance()) < 0) {
//         throw new InsufficientFundsException("Sorry, you do not have enough funds to make this transfer.");
//       }
//       accountDao.updateBalance(transfer.getAccountFromUsername(), senderBalance.subtract(transfer.getBalance()));
//      AccountDao accountDao = new JdbcAccountDao(jdbcTemplate);
//      BigDecimal receiverBalance = accountDao.getBalance(transfer.getAccountFromUsername());
//    }
//}
//
