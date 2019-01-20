package com.volgatech.javacore2018.lab4.supermarket.domain.model.supermarket;

import com.volgatech.javacore2018.lab4.supermarket.domain.exception.NotFoundProductException;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.customer.BaseCustomer;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.product.BaseProduct;

import java.util.Objects;

public class Order {
    private BaseCustomer customer;
    private Basket basket;

    public Order(BaseCustomer customer) {
        this.customer = customer;
        this.basket = new Basket();
    }

    public void addProductToBasket(BaseProduct product, Integer count) {
        basket.addProduct(product, count);
    }

    public void removeProductFromBasket(Integer productId, Integer count) throws NotFoundProductException {
        basket.removeProductCount(productId, count);
    }

    public BaseCustomer getCustomer() {
        return customer;
    }

    public Basket getBasket() {
        return basket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(customer, order.customer) &&
                Objects.equals(basket, order.basket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, basket);
    }

    @Override
    public String toString() {
        return "Order{" +
                "customer=" + customer +
                ", basket=" + basket +
                '}';
    }
}
