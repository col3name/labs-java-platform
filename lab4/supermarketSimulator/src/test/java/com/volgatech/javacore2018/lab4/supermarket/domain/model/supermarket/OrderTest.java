package com.volgatech.javacore2018.lab4.supermarket.domain.model.supermarket;

import com.volgatech.javacore2018.lab4.supermarket.domain.exception.InvalidModelException;
import com.volgatech.javacore2018.lab4.supermarket.domain.exception.NotFoundProductException;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.ExceptionTest;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.customer.Adult;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.customer.BaseCustomer;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.customer.Sex;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.product.BaseProduct;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.product.CountableProduct;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class OrderTest extends ExceptionTest {
    private BaseCustomer customer;
    private Order order;
    private BaseCustomer customer1;

    @Before
    public void init() throws InvalidModelException {
        this.customer = new Adult(0, "Ваня", 45, Sex.MALE);
        this.order = new Order(customer);
        this.customer1 = new Adult(0, "Mikhail", 20, Sex.MALE, BigDecimal.valueOf(1), BigDecimal.valueOf(10), BigDecimal.valueOf(10));
    }

    @Test
    public void compareWithThemselves() {
        assertEquals(order, order);
    }

    @Test
    public void addProductToBasketTest() throws InvalidModelException {
        BaseCustomer customer = new Adult(0, "Ваня", 45, Sex.MALE);
        Order order = new Order(customer);
        CountableProduct product = new CountableProduct(1, "SSD", Adult.MIN_AGE, BigDecimal.valueOf(90.0), BigDecimal.valueOf(0.0));

        order.addProduct(product, 2);
        Map<BaseProduct, Integer> expectedBasket = new LinkedHashMap<>();
        expectedBasket.put(product, 2);
        assertEquals(order.getBasket().getProducts(), expectedBasket);
        assertEquals(order.getCustomer(), customer);
        String actual = "Order{customer=Adult{id=0,  name='Ваня', age=45, sex=MALE, cashBalance=1000.0, cardBalance=1000.0, bonusBalance=1000.0}, basket=BasketRepository{products={CountableProduct{name='SSD', ageLimit=18, price=90.0, bonus=0.0, id=1}=2}}}";
        assertEquals(order.toString(), actual);
    }

    @Test
    public void compareWithNull() {
        assertNotEquals(null, order);
    }

    @Test
    public void compareWithOtherClass() {
        Report report1 = new Report();
        assertNotEquals(order, report1);
    }

    @Test
    public void allFieldEqual() {
        Order order1 = new Order(customer);

        assertEquals(order, order1);
    }

    @Test
    public void allFieldNotEqual() {
        assertNotEquals(order, new Order(customer1));
    }

    @Test
    public void removeProductToBasketTest() throws InvalidModelException, NotFoundProductException {
        BaseCustomer customer = new Adult(0, "Ваня", 45, Sex.MALE);
        Order order = new Order(customer);
        CountableProduct product = new CountableProduct(1, "SSD", Adult.MIN_AGE, BigDecimal.valueOf(90.0), BigDecimal.valueOf(0.0));

        order.addProduct(product, 2);
        order.removeProduct(product.getId(), 2);
        Map<BaseProduct, Integer> expectedBasket = new LinkedHashMap<>();
        expectedBasket.put(product, 0);
        assertEquals(order.getBasket().getProducts(), expectedBasket);
    }
}