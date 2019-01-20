package com.volgatech.javacore2018.lab4.supermarket.applicationservices.services;

import com.volgatech.javacore2018.lab4.supermarket.domain.exception.InvalidModelException;
import com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.services.BillService;
import com.java.lab.domain.model.customer.*;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.product.BaseProduct;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.product.CountableProduct;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.product.ProductWeighted;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.supermarket.Report;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.customer.*;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class BillServiceTest {
    @Test
    public void test() throws InvalidModelException {
        Report report = new Report();

        Map<BaseProduct, Integer> productsInBasket = new HashMap<>();
        productsInBasket.put(new ProductWeighted(4, "Клавиатура", Child.MIN_AGE,
                BigDecimal.valueOf(90.0), BigDecimal.valueOf(1.0)), 3);

        productsInBasket.put(new CountableProduct(1, "SSD", 10, BigDecimal.valueOf(90.0), BigDecimal.valueOf(10.0)), 2);
        productsInBasket.put(new CountableProduct(1, "Алкоголь", Adult.MIN_AGE, BigDecimal.valueOf(90.0), BigDecimal.valueOf(10.0)), 2);

        BaseCustomer customer = new Child(0, "Mikhail", 10, Sex.MALE);
        BillService billService = new BillServiceImpl(report, productsInBasket, customer);
        BigDecimal cashDesk = billService.computeOrderPrice();
        BigDecimal expectedPrice = BigDecimal.valueOf(450.0);
        assertEquals(expectedPrice, cashDesk);
    }


    @Test
    public void retiredTest() throws InvalidModelException {
        Report report = new Report();

        Map<BaseProduct, Integer> productsInBasket = new HashMap<>();
        productsInBasket.put(new ProductWeighted(4, "Клавиатура", Child.MIN_AGE,
                BigDecimal.valueOf(90.0), BigDecimal.valueOf(1.0)), 3);

        productsInBasket.put(new CountableProduct(1, "SSD", 10, BigDecimal.valueOf(90.0), BigDecimal.valueOf(10.0)), 2);
        productsInBasket.put(new CountableProduct(1, "Алкоголь", Adult.MIN_AGE, BigDecimal.valueOf(90.0), BigDecimal.valueOf(10.0)), 2);

        BaseCustomer customer = new Retired(0, "Mikhail", 70, Sex.MALE);
        BillService billService = new BillServiceImpl(report, productsInBasket, customer);
        BigDecimal cashDesk = billService.computeOrderPrice();
        BigDecimal expectedPrice = BigDecimal.valueOf(567.0);
        assertEquals(expectedPrice.toBigInteger(), cashDesk.toBigInteger());
    }
}