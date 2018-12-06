package com.java.lab.domain.model.supermarket.payment;

import com.java.lab.domain.exception.PaymentFailedException;
import com.java.lab.domain.model.customer.BaseCustomer;

import java.math.BigDecimal;

public interface PaymentStrategy {
    void pay(BaseCustomer customer, BigDecimal payAmount) throws PaymentFailedException;
}
