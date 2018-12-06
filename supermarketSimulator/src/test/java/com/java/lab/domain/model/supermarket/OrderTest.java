package com.java.lab.domain.model.supermarket;

import com.java.lab.domain.exception.InvalidModelException;
import com.java.lab.domain.exception.NotFoundProductException;
import com.java.lab.domain.model.ExceptionTest;
import com.java.lab.domain.model.customer.Adult;
import com.java.lab.domain.model.customer.BaseCustomer;
import com.java.lab.domain.model.customer.Sex;
import com.java.lab.domain.model.product.BaseProduct;
import com.java.lab.domain.model.product.CountableProduct;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class OrderTest extends ExceptionTest {
    private BaseCustomer customer;
    private Order order;
    private BaseCustomer customer1;

    public OrderTest() throws InvalidModelException {
        this.customer = new Adult(0, "Ваня", 45, Sex.MALE);
        this.order = new Order(customer);
        this.customer1 = new Adult(0, "Mikhail", 20, Sex.MALE, BigDecimal.valueOf(1), BigDecimal.valueOf(10), BigDecimal.valueOf(10));
    }

    @Test
    public void compareWithThemselves() {

        assertEquals(order, order);
    }

    @Test
    public void compareWithNull() {
        assertFalse(order.equals(null));
    }

    @Test
    public void compareWithOtherClass() {
        Report report1 = new Report();
        assertFalse(order.equals(report1));
    }

    @Test
    public void allFieldEqual() {

        Order order1 = new Order(customer);

        assertTrue(order.equals(order1));
    }

    @Test
    public void allFieldNotEqual() {
        Order order1 = new Order(customer1);
        assertFalse(customer.equals(customer1));
    }

    @Test
    public void addProductToBasketTest() throws InvalidModelException {
        BaseCustomer customer = new Adult(0, "Ваня", 45, Sex.MALE);
        Order order = new Order(customer);
        CountableProduct product = new CountableProduct(1, "SSD", Adult.MIN_AGE, BigDecimal.valueOf(90.0), BigDecimal.valueOf(0.0));

        order.addProductToBasket(product, 2);
        Map<BaseProduct, Integer> expectedBasket = new LinkedHashMap<>();
        expectedBasket.put(product, 2);
        assertEquals(order.getBasket().getProducts(), expectedBasket);
        assertEquals(order.getCustomer(), customer);
        String actual = "Order{customer=Adult{id=0,  name='Ваня', age=45, sex=MALE, cashBalance=1000.0, cardBalance=1000.0, bonusBalance=1000.0}, basket=Basket{products={CountableProduct{name='SSD', ageLimit=18, price=90.0, bonus=0.0, id=1}=2}}}";
        assertEquals(order.toString(), actual);
    }

    @Test
    public void removeProductToBasketTest() throws InvalidModelException, NotFoundProductException {
        BaseCustomer customer = new Adult(0, "Ваня", 45, Sex.MALE);
        Order order = new Order(customer);
        CountableProduct product = new CountableProduct(1, "SSD", Adult.MIN_AGE, BigDecimal.valueOf(90.0), BigDecimal.valueOf(0.0));

        order.addProductToBasket(product, 2);
        order.removeProductFromBasket(product.getId(), 2);
        Map<BaseProduct, Integer> expectedBasket = new LinkedHashMap<>();
        expectedBasket.put(product, 0);
        assertEquals(order.getBasket().getProducts(), expectedBasket);
    }
}