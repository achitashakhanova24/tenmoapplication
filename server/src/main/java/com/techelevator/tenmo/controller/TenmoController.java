package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.DaoException;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.security.Principal;


    //@PreAuthorize("isAuthenticated()")
    @RestController
    public class TenmoController {
    //    @RequestMapping ("/balance")
    //    @Autowired
        AccountDao dao;
        UserDao userDao;
        public TenmoController(AccountDao dao, UserDao userDao) {
            this.dao = dao;
            this.userDao = userDao;
        }


    @RequestMapping(path = "/balance", method = RequestMethod.GET)
    public BigDecimal getBalance(Principal principal) throws DaoException {
        String username = principal.getName();
        int userId = userDao.findIdByUsername(username);
        int accountId = dao.getAccountByUserId(userId).getAccountID();
        BigDecimal balance = dao.getBalance(accountId);
        if (balance == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found.");
        } else {
            return balance;
        }
    }

}
