package com.java.lab.domain.interfaces.repository;

import com.java.lab.domain.exception.NotFoundProductException;
import com.java.lab.domain.model.product.BaseProduct;

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
