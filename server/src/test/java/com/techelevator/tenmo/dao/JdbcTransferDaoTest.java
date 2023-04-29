package com.techelevator.tenmo.dao;

import org.junit.Test;

import static org.junit.Assert.*;

public class JdbcTransferDaoTest {

    @Test
    public void sendTransfer() {
    }


    @Test
    public void getAllTransfersFromUser() {
    }

    @Test
    public void getTransferById() {
    }
    //    *Requestor can cancel transaction
    //*Sender can cancel transaction
    //*Sender can approve transaction
    //- Balances updated upon transaction approval by sender
    //*Sender can cancel transaction
    //- Balances NOT updated
    //*One transaction logged while switching transaction status: sender, receiver, status, amount, timestamp (optional)
    //*ERROR on request from invalid/unknown sender
    //*ERROR on blank amount from client request
    //*ERROR on negative amount from client request
    //*ERROR on 0 amount from client request
    //*ERROR on sending invalid float point values, e.g. $0.001
    //            *ERROR on same receiver and sender from client request
    //*ERROR on balance changes if status PENDING
    //*ERROR on balance changes if status REJECTED
    //*ERROR on receiver updating transaction status
    //*ERORR on sender changing transaction status if not PENDING
    //*ERROR on sender APPROVE with insufficient funds
    //- automatically move status to REJECTED?
    //            *Sender can filter transaction results PENDING/APROVED/REJECTED transactions (optional)
}