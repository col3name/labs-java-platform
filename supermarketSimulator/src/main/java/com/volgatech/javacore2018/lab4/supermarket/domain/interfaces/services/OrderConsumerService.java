package com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.services;

import com.volgatech.javacore2018.lab4.supermarket.domain.exception.PaymentFailedException;
import com.java.lab.domain.interfaces.repository.CustomerRepository;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.supermarket.Order;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.supermarket.Report;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.supermarket.payment.PaymentStrategy;
import com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.repository.CustomerRepository;

public interface OrderConsumerService {
    public void buyProductOrder(Order order, PaymentStrategy paymentStrategy) throws PaymentFailedException;

    public Report getReport();

    public CustomerRepository getCustomerRepository();
}
