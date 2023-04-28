package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.exceptions.InsufficientFundsException;
import com.techelevator.tenmo.exceptions.TransferException;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public interface TransferDao {
    public void sendTransfer (Transfer transfer) throws InsufficientFundsException, IllegalArgumentException, TransferException;

    public List<Transfer> getTransfersForUser(int userId);
    public Transfer getTransferById(int transferId);

    List<Transfer> getAllTransfersFromUser(int userId);
}
