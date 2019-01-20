package com.volgatech.javacore2018.lab4.supermarket.domain.model.customer;

import com.volgatech.javacore2018.lab4.supermarket.domain.exception.InvalidModelException;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.ExceptionTest;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class RetiredTest extends ExceptionTest {
    @Test
    public void createMaleTest() throws InvalidModelException {
        int id = 0;
        String name = "Mikhail";
        int age = 140;
        Sex sex = Sex.MALE;
        Retired adult = new Retired(id, name, age, sex);
        assertEquals((int) adult.getId(), id);
        assertEquals(adult.getName(), name);
        assertEquals((int) adult.getAge(), age);
        assertEquals(adult.getSex(), sex);

        assertEquals(61, (int) adult.getMinAge());
        assertEquals(150, (int) adult.getMaxAge());

        assertEquals("Retired{id=0,  name='Mikhail', age=140, sex=MALE, cashBalance=1000.0, cardBalance=1000.0, bonusBalance=1000.0}", adult.toString());
    }

    @Test
    public void createFemaleTest() throws InvalidModelException {
        int id = 0;
        String name = "Brown";
        int age = 140;
        Sex sex = Sex.FEMALE;
        Retired adult = new Retired(id, name, age, sex);
        assertEquals((int) adult.getId(), id);
        assertEquals(adult.getName(), name);
        assertEquals((int) adult.getAge(), age);
        assertEquals(adult.getSex(), sex);

        assertEquals(54, (int) adult.getMinAge());
        assertEquals(150, (int) adult.getMaxAge());

        assertEquals("Retired{id=0,  name='Brown', age=140, sex=FEMALE, cashBalance=1000.0, cardBalance=1000.0, bonusBalance=1000.0}", adult.toString());
    }

    @Test
    public void
    validRetired() throws InvalidModelException {
        int id = 0;
        String name = "Mikhail";
        int age = 70;
        Sex sex = Sex.MALE;
        BigDecimal cashBalance = BigDecimal.valueOf(1);
        BigDecimal bonus = BigDecimal.valueOf(10);
        BigDecimal cardBalance = BigDecimal.valueOf(100);

        Retired re = new Retired(id, name, age, sex,
                cashBalance, cardBalance, bonus);

        assertEquals((int) re.getId(), id);
        assertEquals(re.getName(), name);
        assertEquals((int) re.getAge(), age);
        assertEquals(re.getSex(), sex);

        assertEquals(re.getCashBalance(), cashBalance);
        assertEquals(re.getCardBalance(), cardBalance);
        assertEquals(re.getBonusBalance(), bonus);
        System.out.println(re.toString());
        assertEquals("Retired{id=0,  name='Mikhail', age=70, sex=MALE, cashBalance=1, cardBalance=100, bonusBalance=10}", re.toString());
    }
}