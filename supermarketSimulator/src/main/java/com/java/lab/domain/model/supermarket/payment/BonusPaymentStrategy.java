package com.java.lab.domain.model.supermarket.payment;

import com.java.lab.domain.exception.PaymentFailedException;
import com.java.lab.domain.model.customer.BaseCustomer;

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

