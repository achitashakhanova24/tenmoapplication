package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.exceptions.InsufficientFundsException;
import com.techelevator.tenmo.exceptions.TransferException;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping ("/transfers")

public class TransferController {

    private TransferDao transferDao;

    public TransferController(TransferDao transferDao) {
        this.transferDao = transferDao;
    }
    @PostMapping("/send")
    public void sendTransfer(@RequestBody Transfer transfer) throws TransferException, InsufficientFundsException {
        transferDao.sendTransfer(transfer);
    }
    @GetMapping("/from/{userId}")
    public List<Transfer> getAllTransfersFromUser(@PathVariable int userId) {
        return transferDao.getAllTransfersFromUser(userId);
    }
    @GetMapping("/{transferId}")
    public Transfer getTransferById(@PathVariable int transferId) {
        return transferDao.getTransferById(transferId);
    }
}

//maybe we will need a getAllTransfersBetweenUsers method as well for the client side
