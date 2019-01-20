package com.volgatech.javacore2018.lab4.supermarket.domain.model.product;

import com.volgatech.javacore2018.lab4.supermarket.domain.exception.InvalidModelException;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.ExceptionTest;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.customer.Adult;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ProductWeightedTest extends ExceptionTest {
    @Test
    public void invalidName() throws InvalidModelException {
        exceptException(InvalidModelException.class, "name min length 3");
       new ProductWeighted(1, "as", Adult.MIN_AGE, BigDecimal.valueOf(90.0), BigDecimal.valueOf(0.0));
    }

    @Test
    public void invalidMinAge() throws InvalidModelException {
        exceptException(InvalidModelException.class, "age must be more than 0");
        new ProductWeighted(1, "SSD", -10, BigDecimal.valueOf(90.0), BigDecimal.valueOf(0.0));
    }

    @Test
    public void invalidPrice() throws InvalidModelException {
        exceptException(InvalidModelException.class, "price must be more than 0");
        new ProductWeighted(1, "SSD", 1, BigDecimal.valueOf(-90.0), BigDecimal.valueOf(0.0));
    }

    @Test
    public void invalidBonus() throws InvalidModelException {
        exceptException(InvalidModelException.class, "bonus must be more than 0");
        new ProductWeighted(1, "SSD", 10, BigDecimal.valueOf(90.0), BigDecimal.valueOf(-10.0));
    }

    @Test
    public void validTest() throws InvalidModelException {
        int id = 1;
        String name = "SSD";
        int ageLimit = 10;
        BigDecimal price = BigDecimal.valueOf(90.0);
        BigDecimal bonus = BigDecimal.valueOf(10.0);
        ProductWeighted product = new ProductWeighted(id, name, ageLimit, price, bonus);
        assertEquals((int) product.getId(), id);
        assertEquals(product.getName(), name);
        assertEquals((int) product.getAgeLimit(), ageLimit);
        assertEquals(product.getPrice(), price);
        assertEquals(product.getBonus(), bonus);
        assertEquals("ProductWeighted{name='SSD', ageLimit=10, price=90.0, bonus=10.0, id=1}", product.toString());
        assertEquals(product, new ProductWeighted(id, name, ageLimit, price, bonus));
    }
}