package com.java.lab.domain.model.product;

import com.java.lab.domain.exception.InvalidModelException;
import com.java.lab.domain.model.ExceptionTest;
import com.java.lab.domain.model.customer.Adult;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertTrue;

public class ProductWeightedTest extends ExceptionTest {
    @Test
    public void invalidName() throws InvalidModelException {
        exceptException(InvalidModelException.class, "name min length 3");
        ProductWeighted product = new ProductWeighted(1, "as", Adult.MIN_AGE, BigDecimal.valueOf(90.0), BigDecimal.valueOf(0.0));
    }

    @Test
    public void invalidMinAge() throws InvalidModelException {
        exceptException(InvalidModelException.class, "age must be more than 0");
        ProductWeighted product = new ProductWeighted(1, "SSD", -10, BigDecimal.valueOf(90.0), BigDecimal.valueOf(0.0));
    }

    @Test
    public void invalidPrice() throws InvalidModelException {
        exceptException(InvalidModelException.class, "price must be more than 0");
        ProductWeighted product = new ProductWeighted(1, "SSD", 1, BigDecimal.valueOf(-90.0), BigDecimal.valueOf(0.0));
    }

    @Test
    public void invalidBonus() throws InvalidModelException {
        exceptException(InvalidModelException.class, "bonus must be more than 0");
        ProductWeighted product = new ProductWeighted(1, "SSD", 10, BigDecimal.valueOf(90.0), BigDecimal.valueOf(-10.0));
    }

    @Test
    public void validTest() throws InvalidModelException {
        int id = 1;
        String name = "SSD";
        int ageLimit = 10;
        BigDecimal price = BigDecimal.valueOf(90.0);
        BigDecimal bonus = BigDecimal.valueOf(10.0);
        ProductWeighted product = new ProductWeighted(id, name, ageLimit, price, bonus);
        assertTrue(product.getId().equals(id));
        assertTrue(product.getName().equals(name));
        assertTrue(product.getAgeLimit().equals(ageLimit));
        assertTrue(product.getPrice().equals(price));
        assertTrue(product.getBonus().equals(bonus));
        assertTrue(product.toString().equals("ProductWeighted{name='SSD', ageLimit=10, price=90.0, bonus=10.0, id=1}"));
        ProductWeighted product1 = product;
        assertTrue(product.equals(product1));
    }
}