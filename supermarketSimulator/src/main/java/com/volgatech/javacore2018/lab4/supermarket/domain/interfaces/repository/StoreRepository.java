package com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.repository;

import com.volgatech.javacore2018.lab4.supermarket.domain.exception.NotFoundProductException;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.product.BaseProduct;

import java.util.Map;

public interface StoreRepository {
    public Map<BaseProduct, Integer> getProducts();

    public Integer size();

    public Map.Entry<BaseProduct, Integer> findById(Integer id);

    public void addProduct(BaseProduct product, Integer count);

    public void update(BaseProduct product, Integer count) throws NotFoundProductException;

    public BaseProduct removeProductCount(Integer productId, Integer count) throws NotFoundProductException;

    public boolean isProductExist(BaseProduct product);
}
