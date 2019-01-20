package com.volgatech.javacore2018.lab4.supermarket.applicationservices.services;

import com.volgatech.javacore2018.lab4.supermarket.applicationservices.services.useraction.OrderGenerator;
import com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.repository.CustomerRepository;
import com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.repository.StoreRepository;
import com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.services.OrderProducerService;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.customer.BaseCustomer;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.supermarket.Order;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class OrderProducerServiceImpl implements OrderProducerService {
    private static final Logger LOG = Logger.getLogger(OrderProducerServiceImpl.class.getName());
    private final OrderGenerator orderGenerator;
    private CustomerRepository customerRepository;
    private StoreRepository storeRepository;

    public OrderProducerServiceImpl(CustomerRepository customerRepository, StoreRepository storeRepository, OrderGenerator orderGenerator) {
        this.customerRepository = customerRepository;
        this.storeRepository = storeRepository;
        this.orderGenerator = orderGenerator;
    }

    @Override
    public Order produce(Integer customerIndex, List<Integer> indexOfProducts) {
        BaseCustomer customer = customerRepository.find(customerIndex);

        String msg = "[" + new Date() + "] New customer ‘" + customer + "’ arrived";
        LOG.info(msg);

        return orderGenerator.takeProducts(customer, indexOfProducts);
    }

    @Override
    public Integer customerRepositorySize() {
        return customerRepository.size();
    }

    @Override
    public Integer storeRepositorySize() {
        return storeRepository.getProducts().size();
    }
}
