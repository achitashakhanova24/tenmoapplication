package com.techelevator.tenmo.exceptions;

public class InsufficientFundsException extends Exception{
    public InsufficientFundsException(){
        super("Sorry, you don't have enough funds to complete the transfer.");
    }
}
