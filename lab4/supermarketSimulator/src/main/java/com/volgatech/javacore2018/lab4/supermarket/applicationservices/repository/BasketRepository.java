package com.volgatech.javacore2018.lab4.supermarket.applicationservices.repository;

public class BasketRepository extends BaseMemoryStoreRepository {
    @Override
    public String toString() {
        return "BasketRepository{" +
                "products=" + products +
                '}';
    }
}