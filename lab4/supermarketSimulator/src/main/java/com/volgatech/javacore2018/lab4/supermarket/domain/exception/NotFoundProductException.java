package com.volgatech.javacore2018.lab4.supermarket.domain.exception;

public class NotFoundProductException extends Exception {
    public NotFoundProductException(String message) {
        super(message);
    }
}
