package com.java.lab.domain.interfaces.services;

import com.java.lab.domain.exception.PaymentFailedException;
import com.java.lab.domain.interfaces.repository.CustomerRepository;
import com.java.lab.domain.model.supermarket.Order;
import com.java.lab.domain.model.supermarket.Report;
import com.java.lab.domain.model.supermarket.payment.PaymentStrategy;

public interface OrderConsumerService {
    public void buyProductOrder(Order order, PaymentStrategy paymentStrategy) throws PaymentFailedException;

    public Report getReport();

    public CustomerRepository getCustomerRepository();
}
