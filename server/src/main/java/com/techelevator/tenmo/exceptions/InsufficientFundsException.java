package com.techelevator.tenmo.exceptions;

public class InsufficientFundsException extends Exception{
    public InsufficientFundsException(String s){
        super("Sorry, you don't have enough funds to complete the transfer.");
    }
}
