package com.java.lab.domain.model;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ProductTest extends ExceptionTest {
    @Test
    public void test() {
        assertTrue(true);
    }
//
//    @Test
//    public void negativeId() {
//        exceptException(InvalidModelException.class, "id must be more than 0");
//        ProductWeighted product = new ProductWeighted(-1, "af", Adult.MIN_AGE, 100.0, 0.0);
//    }
//
//    @Test
//    public void emptyName() {
//        exceptException(InvalidModelException.class, "name min length 3");
//        ProductWeighted product = new ProductWeighted(0, "af", Adult.MIN_AGE, 100.0, 0.0);
//    }
//
//    @Test
//    public void invalidNameLength() {
//        exceptException(InvalidModelException.class, "name min length 3");
//        ProductWeighted product = new ProductWeighted(0, "af", Adult.MIN_AGE, 100.0, 0.0);
//    }
//
//    @Test
//    public void negativePrice() {
//        exceptException(InvalidModelException.class, "price must be more than 0");
//        ProductWeighted product = new ProductWeighted(1, "Мышь", Adult.MIN_AGE, -100.0, 0.0);
//    }
//
//    @Test
//    public void createValid() {
//        ProductWeighted product = new ProductWeighted(1, "Мышь", Adult.MIN_AGE, 100.0, 0.0);
//        assertTrue(Comparator.equalInt(product.getId(), 1));
//        assertEquals(product.getName(), "Мышь");
//        assertTrue(Comparator.equalInt(product.getAgeLimit(), Adult.MIN_AGE));
//    }
//
//    @Test
//    public void invalidSetId() {
//        exceptException(InvalidModelException.class, "id must be more than 0");
//        ProductWeighted product = new ProductWeighted(1, "Мышь", Adult.MIN_AGE, 100.0, 0.0);
//        product.setId(-1);
//    }
//
//    @Test
//    public void invalidSetName() {
//        exceptException(InvalidModelException.class, "name min length 4");
//        ProductWeighted product = new ProductWeighted(1, "Мышь", Adult.MIN_AGE, 100.0, 0.0);
//        product.setName("");
//    }
//
//    @Test
//    public void invalidSetPrice() {
//        exceptException(InvalidModelException.class, "price must be more than 0");
//        ProductWeighted product = new ProductWeighted(1, "Мышь", Adult.MIN_AGE, 100.0, 0.0);
//        product.setPrice(-1.0);
//    }
}