package com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.repository;

import com.volgatech.javacore2018.lab4.supermarket.domain.exception.PaymentFailedException;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.customer.BaseCustomer;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.supermarket.payment.PaymentStrategy;

import java.math.BigDecimal;

public interface CustomerRepository extends Repository<BaseCustomer> {
    Integer size();

    void pay(BaseCustomer customer, PaymentStrategy strategy, BigDecimal price) throws PaymentFailedException;
}
