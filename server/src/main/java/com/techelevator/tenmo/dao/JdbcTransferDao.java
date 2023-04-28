package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.exceptions.InsufficientFundsException;
import com.techelevator.tenmo.exceptions.TransferException;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
    public class JdbcTransferDao implements TransferDao {
    private final AccountDao accountDao;
    private JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate, AccountDao accountDao) {
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
            if (senderBalance.compareTo(transferAmount) < 0) {
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

    @Override
    public List<Transfer> getAllTransfersFromUser(int userId) {
        List<Transfer> transfers = new ArrayList<>();
        String sql = "SELECT transfer.transfer_id, transfer.transfer_type_id, transfer.transfer_status_id, transfer.account_from, transfer.amount_to, transfer.amount, " + "user1.username AS sender, user2.username AS receiver " +
                "ORDER BY transfer.transfer_id DESC";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, userId, userId);
        while (results.next()) {
            transfers.add(mapRowToTransfer(results));
        }
        return transfers;
    }

    @Override
    public Transfer getTransferById(int transferId) {
        Transfer transfer = null;
        String sql = "SELECT transfer.transfer_id, transfer.transfer_type_id, transfer.transfer_status_id, transfer.account_from, transfer.amount_to, transfer.amount, " + "user1.username AS sender, user2.username AS receiver " +
                "FROM transfers transfer " + "JOIN accounts account1 ON transfer.account_from = account1.account_id" + "JOIN accounts account2 ON transfer.account_to = account2.account_id" + "JOIN users user1 ON account1.user_id = user1.user_id" + "JOIN users user2 ON account2.user_id = user2.user_id" + "WHERE transfer.transfer_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, transferId);
        if (results.next()) {
            transfer = mapRowToTransfer(results);
        }
        return transfer;
    }

    private Transfer mapRowToTransfer(SqlRowSet results) {
        Transfer transfer = new Transfer();
        transfer.setTransferId(results.getInt("transfer_id"));
        transfer.setTransferStatus(String.valueOf(results.getInt("transfer_status_id")));
        transfer.setTransferBalance(results.getBigDecimal("balance"));
        transfer.setAccountFrom(results.getInt("account_from"));
        transfer.setAccountTo(results.getInt("account_to"));
        transfer.setMoneyTransfer(results.getBigDecimal("money_transfer"));
        return transfer;
    }
}