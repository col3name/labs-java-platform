package com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.repository;

import com.volgatech.javacore2018.lab4.supermarket.domain.exception.NotFoundProductException;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.product.BaseProduct;

import java.util.Map;

public interface StoreRepository {
    Map<BaseProduct, Integer> getProducts();

    Integer size();

    Map.Entry<BaseProduct, Integer> findById(Integer id);

    void addProduct(BaseProduct product, Integer count);

    void update(BaseProduct product, Integer count) throws NotFoundProductException;

    BaseProduct takeProduct(Integer productId, Integer count) throws NotFoundProductException;

    boolean isUnknownProduct(BaseProduct product);
}
