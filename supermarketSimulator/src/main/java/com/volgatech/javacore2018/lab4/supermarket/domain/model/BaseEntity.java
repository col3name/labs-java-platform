package com.volgatech.javacore2018.lab4.supermarket.domain.model;

import com.volgatech.javacore2018.lab4.supermarket.domain.exception.InvalidModelException;

public abstract class BaseEntity {
    protected Integer id;

    protected BaseEntity(Integer id) throws InvalidModelException {
        tryThrow(id < 0, "id must be more than 0");
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    protected void tryThrow(Boolean condition, String message) throws InvalidModelException {
        if (condition) {
            throw new InvalidModelException(message);
        }
    }
}
