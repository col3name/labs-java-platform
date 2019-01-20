package com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.services;

import com.volgatech.javacore2018.lab4.supermarket.domain.model.supermarket.Order;

import java.util.List;

public interface OrderProducerService {
    public Order produce(Integer index, int number, List<Integer> indexOfProducts);

    public Integer customerRepositorySize();

    public Integer storeRepositorySize();
}
