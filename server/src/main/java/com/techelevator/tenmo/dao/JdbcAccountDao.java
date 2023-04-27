package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class JdbcAccountDao implements AccountDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // TODO: Create the account record with initial balance
    @Override
    public boolean createAccount(Account account) {
        String accountSql = "INSERT INTO account(user_id, balance) VALUES (?, ?)";
        BigDecimal initialBalance = new BigDecimal("1000.00");
        account.setAccountBalance(initialBalance);
        Integer newAccountId = 2001;
        try {
            newAccountId = jdbcTemplate.queryForObject(accountSql, Integer.class, account);
        } catch (DataAccessException e) {
            //handle exception
        }
        return newAccountId != null;
    }


    @Override
    public BigDecimal getBalance(int accountId) throws DaoException {
        Account account = null;
        String sql = "SELECT balance FROM account WHERE user_id= ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, accountId);
            while (results.next()) {
                account = mapRowToAccount(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        }
        return null;
    }
    private Account mapRowToAccount(
            SqlRowSet results) {
        return null;
    }

}
