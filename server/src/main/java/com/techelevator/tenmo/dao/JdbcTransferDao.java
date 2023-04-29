package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfer;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

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
    public Boolean sendTransfer(Transfer transfer) {
        boolean transferSent = false;
        try {
            jdbcTemplate.update("UPDATE account SET balance = balance - ? WHERE account_id = ?",
                    transfer.getMoneyTransfer(), transfer.getAccountFrom());
            jdbcTemplate.update("UPDATE account SET balance = balance + ? WHERE account_id = ?",
                    transfer.getMoneyTransfer(), transfer.getAccountTo());
            jdbcTemplate.update("INSERT INTO transfer (account_from, account_to, money_transfer, transfer_status) " + "Values(?,?,?,?)", transfer.getAccountFrom(), transfer.getAccountTo(), transfer.getMoneyTransfer(), transfer.getTransferStatus());
            transferSent = true;
        } catch (DataAccessException e) {
            System.out.println(e.getMessage());
        }
        return transferSent;
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
                "FROM transfers transfer " + "JOIN accounts account1 ON transfer.account_from = account1.account_id " + "JOIN accounts account2 ON transfer.account_to = account2.account_id " + "JOIN users user1 ON account1.user_id = user1.user_id " + "JOIN users user2 ON account2.user_id = user2.user_id " + "WHERE transfer.transfer_id = ?";
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
        transfer.setMoneyTransfer(results.getBigDecimal("balance"));
        transfer.setAccountFrom(results.getInt("account_from"));
        transfer.setAccountTo(results.getInt("account_to"));
        return transfer;
    }
}