package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public abstract class JdbcUserDao implements UserDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int findIdByUsername(String username) {
        String sql = "SELECT user_id FROM tenmo_user WHERE username ILIKE ?;";
        Integer id = jdbcTemplate.queryForObject(sql, Integer.class, username);
        if (id != null) {
            return id;
        } else {
            return -1;
        }
    }

    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT user_id, username, password_hash FROM tenmo_user;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            User user = mapRowToUser(results);
            users.add(user);
        }
        return users;
    }

    @Override
    public User findByUsername(String username) throws UsernameNotFoundException {
        String sql = "SELECT user_id, username, password_hash FROM tenmo_user WHERE username ILIKE ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, username);
        if (rowSet.next()) {
            return mapRowToUser(rowSet);
        }
        throw new UsernameNotFoundException("User " + username + " was not found.");
    }

    @Override
    public boolean create(String username, String password) {

        // create user
        String sql = "INSERT INTO tenmo_user (username, password_hash) VALUES (?, ?) RETURNING user_id";
        String password_hash = new BCryptPasswordEncoder().encode(password);
        Integer newUserId;
        try {
            newUserId = jdbcTemplate.queryForObject(sql, Integer.class, username, password_hash);
        } catch (DataAccessException e) {
        }
        return false;
    }


    // TODO: Create the account record with initial balance
    @Override
    public boolean createAccount(int accountID, int userID, BigDecimal accountBalance, String accountPassword) {
        // create account
        String accountSql = "INSERT INTO account(user_id, balance, password_hash) VALUES (?, ?, ? )";
        BigDecimal initialBalance = new BigDecimal("1000.00");
        String accountPassword_hash = new BCryptPasswordEncoder().encode(accountPassword);
        Integer newAccountId;
        try {
            newAccountId = jdbcTemplate.queryForObject(accountSql, Integer.class, userID, accountBalance.add(initialBalance), accountPassword_hash);
        } catch (DataAccessException e) {
        }
        return false;
    }

    @Override
    public BigDecimal getBalance(int accountId) throws DaoException {
        Account account = null;
        String sql = "SELECT balance FROM account WHERE user_id= ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, accountId);
            while (results.next()) {
                User user = mapRowToUser(results);
                account = mapRowToAccount(results, user);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (BadSqlGrammarException e) {
            throw new DaoException("SQL syntax error", e);
        }
        return null;
    }
    private Account mapRowToAccount(
            SqlRowSet results, User user) {
        return null;
    }

    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setId(rs.getInt("user_id"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password_hash"));
        user.setActivated(true);
        user.setAuthorities("USER");
        return user;
    }
}





//    @Override
//    public List<Hotel> list() {
//        List<Hotel> hotelList = new ArrayList<>();
//        String sql = "SELECT h.id, h.name, h.stars, h.rooms_available, " +
//                "h.cost_per_night, h.cover_img, a.id AS address_id, a.address, a.city, a.state, a.zip from hotel h JOIN address a ON " +
//                "h.address_id = a.id";
//
//        try {
//            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
//            while (results.next()) {
//                Address address = mapRowToAddress(results);
//                Hotel hotel = mapRowToHotel(results, address);
//                hotelList.add(hotel);
//            }
//        } catch (CannotGetJdbcConnectionException e) {
//            throw new DaoException("Unable to connect to server or database", e);
//        } catch (BadSqlGrammarException e) {
//            throw new DaoException("SQL syntax error", e);
//        }
//        return hotelList;
//    }
//
//
//
//    @Override
//    public Reservation update(Reservation reservation, int reservationID) {
//        Reservation newReservation = null;
//        String sql = "UPDATE reservations SET " +
//                "full_name=?, check_in_date=?, check_out_date=?, guests=? " +
//                "WHERE id = ?";
//
//        int guests = reservation.getGuests();
//        String fullName = reservation.getFullName();
//        String checkInDate = reservation.getCheckinDate();
//        String checkoutDate = reservation.getCheckoutDate();
//
//        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate dateLD = LocalDate.parse(checkInDate, format);
//        LocalDate checkOutDate = LocalDate.parse(checkoutDate, format);
//
//        try {
//            int rowsAffected = jdbcTemplate.update(sql, fullName, dateLD, checkOutDate, guests, reservationID);
//            if(rowsAffected == 0){
//                throw new DaoException("ERROR updating reservation. Reservation not updated");
//            } else {
//                newReservation = get(reservationID);
//            }
//        } catch (CannotGetJdbcConnectionException e) {
//            throw new DaoException("Unable to connect to server or database", e);
//        } catch (BadSqlGrammarException e) {
//            throw new DaoException("SQL syntax error", e);
//        } catch (DataIntegrityViolationException e) {
//            throw new DaoException("Data integrity violation", e);
//        }
//
//        return newReservation;
//    }
//
//    @Override
//    public void delete(int reservationId) {
//        String sql = "DELETE FROM reservations WHERE id = ?";
//
//        try {
//            jdbcTemplate.update(sql, reservationId);
//        } catch (CannotGetJdbcConnectionException e) {
//            throw new DaoException("Unable to connect to server or database", e);
//        } catch (BadSqlGrammarException e) {
//            throw new DaoException("SQL syntax error", e);
//        } catch (DataIntegrityViolationException e) {
//            throw new DaoException("Data integrity violation", e);
//        }
//    }



