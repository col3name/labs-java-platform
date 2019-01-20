package com.volgatech.javacore2018.lab4.supermarket.domain.model.product;

import com.volgatech.javacore2018.lab4.supermarket.domain.exception.InvalidModelException;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.ExceptionTest;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.customer.Adult;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CountableProductTest extends ExceptionTest {
    @Test
    public void invalidId() throws InvalidModelException {
        exceptException(InvalidModelException.class, "id must be more than 0");
        CountableProduct product = new CountableProduct(-1, "sas", Adult.MIN_AGE, BigDecimal.valueOf(90.0), BigDecimal.valueOf(0.0));
    }

    @Test
    public void invalidName() throws InvalidModelException {
        exceptException(InvalidModelException.class, "name min length 3");
        CountableProduct product = new CountableProduct(1, "as", Adult.MIN_AGE, BigDecimal.valueOf(90.0), BigDecimal.valueOf(0.0));
    }

    @Test
    public void invalidMinAge() throws InvalidModelException {
        exceptException(InvalidModelException.class, "age must be more than 0");
        CountableProduct product = new CountableProduct(1, "SSD", -10, BigDecimal.valueOf(90.0), BigDecimal.valueOf(0.0));
    }

    @Test
    public void invalidPrice() throws InvalidModelException {
        exceptException(InvalidModelException.class, "price must be more than 0");
        CountableProduct product = new CountableProduct(1, "SSD", 1, BigDecimal.valueOf(-90.0), BigDecimal.valueOf(0.0));
    }

    @Test
    public void invalidBonus() throws InvalidModelException {
        exceptException(InvalidModelException.class, "bonus must be more than 0");
        CountableProduct product = new CountableProduct(1, "SSD", 10, BigDecimal.valueOf(90.0), BigDecimal.valueOf(-10.0));
    }

    @Test
    public void compareWithThemselves() throws InvalidModelException {
        CountableProduct product = new CountableProduct(1, "SSD", 10, BigDecimal.valueOf(90.0), BigDecimal.valueOf(10.0));
        CountableProduct product1 = product;
        assertTrue(product.equals(product1));
    }

    @Test
    public void compareWithNull() throws InvalidModelException {
        CountableProduct product = new CountableProduct(1, "SSD", 10, BigDecimal.valueOf(90.0), BigDecimal.valueOf(10.0));
        assertFalse(product.equals(null));
    }

    @Test
    public void compareWithThemselvesCopy() throws InvalidModelException {
        CountableProduct product = new CountableProduct(1, "SSD", 10, BigDecimal.valueOf(90.0), BigDecimal.valueOf(10.0));
        CountableProduct product1 = product;
        assertTrue(product.equals(product1));
    }

    @Test
    public void compareWithOtherProduct() throws InvalidModelException {
        CountableProduct product1 = new CountableProduct(1, "SSD", 10, BigDecimal.valueOf(90.0), BigDecimal.valueOf(10.0));
        CountableProduct product2 = new CountableProduct(1, "SSD", 10, BigDecimal.valueOf(90.0), BigDecimal.valueOf(10.0));
        assertTrue(product2.equals(product1));
    }

    @Test
    public void validTest() throws InvalidModelException {
        int id = 1;
        String name = "SSD";
        int ageLimit = 10;
        BigDecimal price = BigDecimal.valueOf(90.0);
        BigDecimal bonus = BigDecimal.valueOf(10.0);
        CountableProduct product = new CountableProduct(id, name, ageLimit, price, bonus);
        assertEquals((int) product.getId(), id);
        assertEquals(product.getName(), name);
        assertEquals((int) product.getAgeLimit(), ageLimit);
        assertEquals(product.getPrice(), price);
        assertEquals(product.getBonus(), bonus);
        assertEquals("CountableProduct{name='SSD', ageLimit=10, price=90.0, bonus=10.0, id=1}", product.toString());
        CountableProduct product1 = product;
        assertEquals(product, product1);
    }
}