package com.volgatech.javacore2018.lab4.supermarket.domain.model;

import org.junit.Rule;
import org.junit.rules.ExpectedException;

public abstract class ExceptionTest {
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    public void exceptException(Class<? extends Throwable> type, String message) {
        expectedEx.expect(type);
        expectedEx.expectMessage(message);
    }
}
