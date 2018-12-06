package com.java.lab.domain.model.customer;

import com.java.lab.domain.exception.InvalidModelException;
import com.java.lab.domain.model.ExceptionTest;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;


public class ChildTest extends ExceptionTest {
    @Test
    public void createTest() throws InvalidModelException {
        int id = 0;
        String name = "Mikhail";
        int age = 15;
        Sex sex = Sex.MALE;
        Child child = new Child(id, name, age, sex);
        assertEquals((int) child.getId(), id);
        assertEquals(child.getName(), name);
        assertEquals((int) child.getAge(), age);
        assertEquals(child.getSex(), sex);

        assertEquals(17, (int) child.getMaxAge());
        assertEquals(9, (int) child.getMinAge());
        assertEquals("Child{id=0,  name='Mikhail', age=15, sex=MALE, cashBalance=1000.0, cardBalance=1000.0, bonusBalance=1000.0}", child.toString());
    }

    @Test
    public void validChild() throws InvalidModelException {
        int id = 0;
        String name = "Mikhail";
        int age = 10;
        Sex sex = Sex.MALE;
        BigDecimal cashBalance = BigDecimal.valueOf(1);
        BigDecimal bonus = BigDecimal.valueOf(10);
        BigDecimal cardBalance = BigDecimal.valueOf(100);
        Child child = new Child(id, name, age, sex,
                cashBalance, cardBalance, bonus);

        assertEquals((int) child.getId(), id);
        assertEquals(child.getName(), name);
        assertEquals((int) child.getAge(), age);
        assertEquals(child.getSex(), sex);

        assertEquals(child.getCashBalance(), cashBalance);
        assertEquals(child.getCardBalance(), cardBalance);
        assertEquals(child.getBonusBalance(), bonus);
        assertEquals("Child{id=0,  name='Mikhail', age=10, sex=MALE, cashBalance=1, cardBalance=100, bonusBalance=10}", child.toString());
    }
}