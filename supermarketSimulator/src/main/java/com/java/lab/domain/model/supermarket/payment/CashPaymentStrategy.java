package com.java.lab.domain.model.supermarket.payment;

import com.java.lab.domain.exception.PaymentFailedException;
import com.java.lab.domain.model.customer.BaseCustomer;

import java.math.BigDecimal;

public class CashPaymentStrategy implements PaymentStrategy {
    @Override
    public void pay(BaseCustomer customer, BigDecimal payAmount) throws PaymentFailedException {
        customer.payCash(payAmount);
    }

    @Override
    public String toString() {
        return "cash payment";
    }
}
