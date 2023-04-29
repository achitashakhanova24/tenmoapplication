package com.techelevator.tenmo.exceptions;

import org.springframework.dao.DataAccessException;

public class DaoException extends Throwable {
    public DaoException(Object p0, DataAccessException e) {
    }
}
