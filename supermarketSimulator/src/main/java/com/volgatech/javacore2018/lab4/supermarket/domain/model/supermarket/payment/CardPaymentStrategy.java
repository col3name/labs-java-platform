package com.volgatech.javacore2018.lab4.supermarket.domain.model.supermarket.payment;

import com.volgatech.javacore2018.lab4.supermarket.domain.exception.PaymentFailedException;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.customer.BaseCustomer;

import java.math.BigDecimal;

public class CardPaymentStrategy implements PaymentStrategy {
    @Override
    public void pay(BaseCustomer customer, BigDecimal payAmount) throws PaymentFailedException {
        customer.payCard(payAmount);
    }

    @Override
    public String toString() {
        return "card payment";
    }
}
