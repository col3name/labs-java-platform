package com.volgatech.javacore2018.lab4.supermarket.domain.exception;

public class PaymentFailedException extends Exception {
    public PaymentFailedException(String message) {
        super(message);
    }
}
