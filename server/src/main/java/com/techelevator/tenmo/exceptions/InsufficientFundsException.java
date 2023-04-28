package com.techelevator.tenmo.exceptions;

import org.springframework.http.HttpStatus;

public class InsufficientFundsException extends Exception{
    public InsufficientFundsException(HttpStatus badRequest, String s){
        super("Sorry, you don't have enough funds to complete the transfer.");
    }
}
