package com.java.lab.domain.interfaces.repository;

import com.java.lab.domain.exception.PaymentFailedException;
import com.java.lab.domain.model.customer.BaseCustomer;
import com.java.lab.domain.model.supermarket.payment.PaymentStrategy;

import java.math.BigDecimal;

public interface CustomerRepository extends Repository<BaseCustomer> {
    Integer size();

    void pay(BaseCustomer customer, PaymentStrategy strategy, BigDecimal price) throws PaymentFailedException;
}
