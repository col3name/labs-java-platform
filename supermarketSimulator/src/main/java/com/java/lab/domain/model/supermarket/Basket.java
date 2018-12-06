package com.java.lab.domain.model.supermarket;

import com.java.lab.applicationservices.repository.BaseMemoryStoreRepository;

public class Basket extends BaseMemoryStoreRepository {
    @Override
    public String toString() {
        return "Basket{" +
                "products=" + products +
                '}';
    }
}
