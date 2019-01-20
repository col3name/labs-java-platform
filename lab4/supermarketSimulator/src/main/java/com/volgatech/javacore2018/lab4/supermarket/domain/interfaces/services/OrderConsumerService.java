package com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.services;

import com.volgatech.javacore2018.lab4.supermarket.domain.exception.PaymentFailedException;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.supermarket.Order;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.supermarket.Report;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.supermarket.payment.PaymentStrategy;

public interface OrderConsumerService {
    void payOrder(Order order, PaymentStrategy paymentStrategy) throws PaymentFailedException;

    Report getReport();
}
