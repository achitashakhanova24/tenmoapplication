package com.techelevator.tenmo.dao;

import org.springframework.dao.DataAccessException;

public class DaoException extends Throwable {
    public DaoException(Object p0, DataAccessException e) {
    }
}
