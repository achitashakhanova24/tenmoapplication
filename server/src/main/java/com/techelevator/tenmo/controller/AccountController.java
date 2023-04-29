package com.techelevator.tenmo.controller;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.exceptions.DaoException;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.dao.UserDao;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.security.Principal;


        //@PreAuthorize("isAuthenticated()")
        @RestController
        public class AccountController {

        //@Autowired
        AccountDao accountDao;
        UserDao userDao;
        TransferDao transferDao;

        public AccountController(AccountDao dao, UserDao userDao, TransferDao transferDao) {
            this.accountDao = dao;
            this.userDao = userDao;
            this.transferDao = transferDao;
        }


        @RequestMapping(path = "/balance", method = RequestMethod.GET)
        public BigDecimal getBalance(Principal principal) throws DaoException {
            String username = principal.getName();
            int userId = userDao.findIdByUsername(username);
            int accountId = accountDao.getAccountByUserId(userId).getAccountID();
            BigDecimal balance = accountDao.getBalance(accountId);
            if (balance == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found.");
            } else {
                return balance;
            }
        }
}
