package com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.services;

import com.volgatech.javacore2018.lab4.supermarket.domain.model.supermarket.Order;

import java.util.List;

public interface OrderProducerService {
    Order produce(Integer index, List<Integer> indexOfProducts);

    Integer customerRepositorySize();

    Integer storeRepositorySize();
}
