package com.volgatech.javacore2018.lab4.supermarket.domain.model.supermarket;

import com.volgatech.javacore2018.lab4.supermarket.domain.exception.InvalidModelException;
import com.volgatech.javacore2018.lab4.supermarket.domain.exception.NotFoundProductException;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.ExceptionTest;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.customer.Adult;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.product.BaseProduct;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.product.CountableProduct;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class BasketTest extends ExceptionTest {

    @Test
    public void addNotExistProductTest() throws InvalidModelException {
        Basket basket = new Basket();
        CountableProduct product = new CountableProduct(1, "SSD", Adult.MIN_AGE, BigDecimal.valueOf(90.0), BigDecimal.valueOf(0.0));
        int count = 10;
        basket.addProduct(product, count);
        Map<BaseProduct, Integer> expectedProduct = new LinkedHashMap<>();
        expectedProduct.put(product, count);
        assertProduct(basket, expectedProduct,
                "Basket{products={CountableProduct{name='SSD', ageLimit=18, price=90.0, bonus=0.0, id=1}=10}}");
    }


    @Test
    public void addExistProductTest() throws InvalidModelException {
        Basket basket = new Basket();
        CountableProduct product = new CountableProduct(1, "SSD", Adult.MIN_AGE, BigDecimal.valueOf(90.0), BigDecimal.valueOf(0.0));
        int count = 10;
        basket.addProduct(product, count);
        basket.addProduct(product, count);
        Map<BaseProduct, Integer> expectedProduct = new LinkedHashMap<>();
        int expectedProductCount = 20;
        expectedProduct.put(product, expectedProductCount);

        assertProduct(basket, expectedProduct,
                "Basket{products={CountableProduct{name='SSD', ageLimit=18, price=90.0, bonus=0.0, id=1}=20}}");
    }

    @Test
    public void updateNotExistProductTest() throws InvalidModelException, NotFoundProductException {
        exceptException(NotFoundProductException.class, "Not found: ");
        Basket basket = new Basket();
        CountableProduct product = new CountableProduct(1, "SSD", Adult.MIN_AGE, BigDecimal.valueOf(90.0), BigDecimal.valueOf(0.0));
        int count = 10;
        basket.update(product, count);
    }

    @Test
    public void updateExistProductTest() throws InvalidModelException, NotFoundProductException {
        Basket basket = new Basket();
        CountableProduct product = new CountableProduct(1, "SSD", Adult.MIN_AGE, BigDecimal.valueOf(90.0), BigDecimal.valueOf(0.0));
        int count = 10;
        basket.addProduct(product, count);
        int expectedProductCount = 8;
        basket.update(product, expectedProductCount);
        Map<BaseProduct, Integer> expectedProduct = new LinkedHashMap<>();
        expectedProduct.put(product, expectedProductCount);
        assertProduct(basket, expectedProduct,
                "Basket{products={CountableProduct{name='SSD', ageLimit=18, price=90.0, bonus=0.0, id=1}=8}}");
    }

    @Test
    public void removeUnExistProduct() throws InvalidModelException, NotFoundProductException {
        exceptException(NotFoundProductException.class, "Unknown product");
        Basket basket = new Basket();
        CountableProduct product = new CountableProduct(1, "SSD", Adult.MIN_AGE, BigDecimal.valueOf(90.0), BigDecimal.valueOf(0.0));
        int count = 10;
        BaseProduct newProduct = basket.removeProductCount(product.getId(), count);
    }

    @Test
    public void removeNotEnoughProduct() throws InvalidModelException, NotFoundProductException {
        exceptException(NotFoundProductException.class, "not enough products. ");
        Basket basket = new Basket();
        CountableProduct product = new CountableProduct(1, "SSD", Adult.MIN_AGE, BigDecimal.valueOf(90.0), BigDecimal.valueOf(0.0));
        int count = 10;
        basket.addProduct(product, count);
        BaseProduct newProduct = basket.removeProductCount(product.getId(), count + 1);
    }

    @Test
    public void removeValidProduct() throws InvalidModelException, NotFoundProductException {
        Basket basket = new Basket();
        CountableProduct product = new CountableProduct(1, "SSD", Adult.MIN_AGE, BigDecimal.valueOf(90.0), BigDecimal.valueOf(0.0));
        int count = 10;
        basket.addProduct(product, count);
        basket.removeProductCount(product.getId(), 2);
        Map<BaseProduct, Integer> expectedProduct = new LinkedHashMap<>();
        expectedProduct.put(product, 8);
        assertProduct(basket, expectedProduct,
                "Basket{products={CountableProduct{name='SSD', ageLimit=18, price=90.0, bonus=0.0, id=1}=8}}");
    }

    private void assertProduct(Basket basket, Map<BaseProduct, Integer> expectedProduct, String s) {
        assertEquals(1, (int) basket.size());
        assertEquals(basket.getProducts(), expectedProduct);
        assertEquals(basket.toString(), s);
    }
}