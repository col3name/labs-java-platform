package com.java.lab.domain.interfaces.services;

import com.java.lab.domain.model.supermarket.Order;

import java.util.List;

public interface OrderProducerService {
    public Order produce(Integer index, int number, List<Integer> indexOfProducts);

    public Integer customerRepositorySize();

    public Integer storeRepositorySize();
}
