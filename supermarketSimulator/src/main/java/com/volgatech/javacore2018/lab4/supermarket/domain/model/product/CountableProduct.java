package com.volgatech.javacore2018.lab4.supermarket.domain.model.product;

import com.volgatech.javacore2018.lab4.supermarket.domain.exception.InvalidModelException;

import java.math.BigDecimal;

public class CountableProduct extends BaseProduct implements CountableSale {
    public CountableProduct(Integer id, String name, Integer ageLimit, BigDecimal price, BigDecimal bonus) throws InvalidModelException {
        super(id, name, ageLimit, price, bonus);
    }

    @Override
    public String toString() {
        return "CountableProduct{" +
                "name='" + name + '\'' +
                ", ageLimit=" + ageLimit +
                ", price=" + price +
                ", bonus=" + bonus +
                ", id=" + id +
                '}';
    }
}
