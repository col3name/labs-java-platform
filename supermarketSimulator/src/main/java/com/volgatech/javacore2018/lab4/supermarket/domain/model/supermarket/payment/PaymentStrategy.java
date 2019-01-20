package com.volgatech.javacore2018.lab4.supermarket.domain.model.supermarket.payment;

import com.volgatech.javacore2018.lab4.supermarket.domain.exception.PaymentFailedException;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.customer.BaseCustomer;

import java.math.BigDecimal;

public interface PaymentStrategy {
    void pay(BaseCustomer customer, BigDecimal payAmount) throws PaymentFailedException;
}
