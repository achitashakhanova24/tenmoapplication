package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.DaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

@PreAuthorize("isAuthenticated()")
@RestController
public class TenmoController {

    @Autowired
    AccountDao dao;

    // I need a mapping that will call
    // the account dao's get balance method
    // this amount will be returned to whoever made the call
    @RequestMapping(path = "/balance/{userId}", method = RequestMethod.GET)
    public BigDecimal getBalance(@PathVariable int userId) throws DaoException {
      BigDecimal balance = dao.getBalance(userId);
        if (balance == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found.");
        } else {
            return balance;
        }
    }
}
