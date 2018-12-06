package com.java.lab.domain.model.product;

import com.java.lab.domain.exception.InvalidModelException;

import java.math.BigDecimal;

public class ProductWeighted extends BaseProduct implements WeightedSale {
    public ProductWeighted(Integer id, String name, Integer ageLimit, BigDecimal price, BigDecimal bonus) throws InvalidModelException {
        super(id, name, ageLimit, price, bonus);
    }

    @Override
    public String toString() {
        return "ProductWeighted{" +
                "name='" + name + '\'' +
                ", ageLimit=" + ageLimit +
                ", price=" + price +
                ", bonus=" + bonus +
                ", id=" + id +
                '}';
    }
}
