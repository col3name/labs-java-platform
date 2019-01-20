package com.volgatech.javacore2018.lab4.supermarket.domain.model.supermarket.payment;

import com.volgatech.javacore2018.lab4.supermarket.domain.exception.PaymentFailedException;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.customer.BaseCustomer;

import java.math.BigDecimal;

public class BonusPaymentStrategy implements PaymentStrategy {
    @Override
    public void pay(BaseCustomer customer, BigDecimal payAmount) throws PaymentFailedException {
        customer.payBonus(payAmount);
    }

    @Override
    public String toString() {
        return "bonus payment";
    }
}

