package com.techelevator.tenmo.exceptions;

import org.springframework.http.HttpStatus;

public class TransferException extends  Exception {
    public TransferException(HttpStatus badRequest, String s){
        super("Sorry, you are broke.");
    }
}
