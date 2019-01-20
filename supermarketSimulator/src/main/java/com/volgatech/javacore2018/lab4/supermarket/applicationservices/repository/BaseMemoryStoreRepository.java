package com.volgatech.javacore2018.lab4.supermarket.applicationservices.repository;

import com.volgatech.javacore2018.lab4.supermarket.domain.exception.NotFoundProductException;
import com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.repository.StoreRepository;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.product.BaseProduct;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public abstract class BaseMemoryStoreRepository implements StoreRepository {
    protected Map<BaseProduct, Integer> products = new LinkedHashMap<>();

    public BaseMemoryStoreRepository() {
    }

    @Override
    public Map<BaseProduct, Integer> getProducts() {
        return products;
    }

    @Override
    public Integer size() {
        return products.size();
    }

    @Override
    public void addProduct(BaseProduct product, Integer count) {
        if (!isProductExist(product)) {
            products.put(product, count);
        } else {
            add(product, count);
        }
    }

    @Override
    public void update(BaseProduct product, Integer count) throws NotFoundProductException {
        if (!isProductExist(product)) {
            throw new NotFoundProductException("Not found: " + product);
        }

        products.put(product, count);
    }

    @Override
    public BaseProduct removeProductCount(Integer productId, Integer count) throws NotFoundProductException {
        Map.Entry<BaseProduct, Integer> productEntry = findById(productId);

        if (productEntry == null) {
            throw new NotFoundProductException("Unknown product");
        }

        Integer productExist = productEntry.getValue();
        BaseProduct product = productEntry.getKey();

        if (productExist < count) {
            throw new NotFoundProductException("not enough products. " + product);
        }

        products.put(product, productExist - count);
        return product;
    }

    @Override
    public boolean isProductExist(BaseProduct product) {
        return products.containsKey(product);
    }

    private void add(BaseProduct product, Integer count) {
        products.put(product, products.get(product) + count);
    }

    @Override
    public Map.Entry<BaseProduct, Integer> findById(Integer productId) {
        Map.Entry<BaseProduct, Integer> productEntry = null;

        for (Map.Entry<BaseProduct, Integer> mapEntry : products.entrySet()) {
            if (mapEntry.getKey().getId().equals(productId)) {
                productEntry = mapEntry;
                break;
            }
        }

        return productEntry;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseMemoryStoreRepository that = (BaseMemoryStoreRepository) o;
        return Objects.equals(products, that.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(products);
    }
}
