package com.java.lab.domain.model.customer;

import com.java.lab.domain.exception.InvalidModelException;
import com.java.lab.domain.exception.PaymentFailedException;
import com.java.lab.domain.model.ExceptionTest;
import com.java.lab.domain.model.supermarket.Report;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class AdultTest extends ExceptionTest {

    private BaseCustomer customer;

    public AdultTest() throws InvalidModelException {
        customer = new Adult(0, "Mikhail", 20, Sex.MALE);
    }

    @Test
    public void compareWithThemselves() {
        assertEquals(customer, customer);
    }

    @Test
    public void compareWithNull() {
        assertFalse(customer.equals(null));
    }

    @Test
    public void compareWithOtherClass() {
        Report report1 = new Report();
        assertFalse(customer.equals(report1));
    }

    @Test
    public void allFieldEqual() throws InvalidModelException {
        BaseCustomer customer1 = new Adult(0, "Mikhail", 20, Sex.MALE);

        assertEquals(customer, customer1);
    }

    @Test
    public void notEqualId() throws InvalidModelException {
        BaseCustomer customer1 = new Adult(1, "Alex", 22, Sex.FEMALE);

        assertFalse(customer.equals(customer1));
    }

    @Test
    public void notEqualName() throws InvalidModelException {
        BaseCustomer customer1 = new Adult(0, "Alex", 20, Sex.MALE);

        assertNotEquals(customer, customer1);
    }

    @Test
    public void notEqualAge() throws InvalidModelException {
        BaseCustomer customer1 = new Adult(0, "Mikhail", 42, Sex.MALE);

        assertNotEquals(customer, customer1);
    }

    @Test
    public void notEqualSex() throws InvalidModelException {
        BaseCustomer customer1 = new Adult(0, "Mikhail", 20, Sex.FEMALE);

        assertNotEquals(customer, customer1);
    }


    @Test
    public void createTest() throws InvalidModelException {
        int id = 0;
        String name = "Mikhail";
        int age = 20;
        Sex sex = Sex.MALE;
        BaseCustomer adult = new Adult(id, name, age, sex);
        assertEquals((int) adult.getId(), id);
        assertEquals(adult.getName(), name);
        assertEquals((int) adult.getAge(), age);
        assertEquals(adult.getSex(), sex);

        assertEquals(18, (int) adult.getMinAge());
        assertEquals(60, (int) adult.getMaxAge());
        assertEquals("Adult{id=0,  name='Mikhail', age=20, sex=MALE, cashBalance=1000.0, cardBalance=1000.0, bonusBalance=1000.0}", adult.toString());
    }

    @Test
    public void createFeMaleTest() throws InvalidModelException {
        int id = 0;
        String name = "Alex";
        int age = 20;
        Sex sex = Sex.FEMALE;
        Adult adult = new Adult(id, name, age, sex);
        assertEquals((int) adult.getId(), id);
        assertEquals(adult.getName(), name);
        assertEquals((int) adult.getAge(), age);
        assertEquals(adult.getSex(), sex);

        assertEquals(18, (int) adult.getMinAge());
        assertEquals(53, (int) adult.getMaxAge());
        assertEquals("Adult{id=0,  name='Alex', age=20, sex=FEMALE, cashBalance=1000.0, cardBalance=1000.0, bonusBalance=1000.0}", adult.toString());
    }

    @Test
    public void invalidCashBalance() throws InvalidModelException {
        exceptException(InvalidModelException.class, "cashBalance must be more than 0");
        Adult adult = new Adult(0, "Mikhail", 20, Sex.MALE,
                BigDecimal.valueOf(-1), BigDecimal.valueOf(10), BigDecimal.valueOf(10));
    }

    @Test
    public void invalidCardBalance() throws InvalidModelException {
        exceptException(InvalidModelException.class, "cardBalance must be more than 0");
        Adult adult = new Adult(0, "Mikhail", 20, Sex.MALE,
                BigDecimal.valueOf(10), BigDecimal.valueOf(-10), BigDecimal.valueOf(10));
    }

    @Test
    public void invalidBonus() throws InvalidModelException {
        exceptException(InvalidModelException.class, "bonus must be more than 0");
        Adult adult = new Adult(0, "Mikhail", 20, Sex.MALE,
                BigDecimal.valueOf(1), BigDecimal.valueOf(10), BigDecimal.valueOf(-10));
    }

    @Test
    public void validAdult() throws InvalidModelException {
        int id = 0;
        String name = "Mikhail";
        int age = 20;
        Sex sex = Sex.MALE;
        BigDecimal cashBalance = BigDecimal.valueOf(1);
        BigDecimal bonus = BigDecimal.valueOf(10);
        BigDecimal cardBalance = BigDecimal.valueOf(100);
        Adult adult = new Adult(0, "Mikhail", 20, Sex.MALE,
                cashBalance, cardBalance, bonus);
        assertTrue(adult.getId().equals(id));
        assertTrue(adult.getName().equals(name));
        assertTrue(adult.getAge().equals(age));
        assertTrue(adult.getSex().equals(sex));

        assertTrue(adult.getMinAge().equals(18));
        assertTrue(adult.getMaxAge().equals(60));
        assertTrue(adult.getCashBalance().equals(cashBalance));
        assertTrue(adult.getCardBalance().equals(cardBalance));
        assertTrue(adult.getBonusBalance().equals(bonus));
        System.out.println(adult.toString());
        assertTrue(adult.toString().equals("Adult{id=0,  name='Mikhail', age=20, sex=MALE, cashBalance=1, cardBalance=100, bonusBalance=10}"));
    }

    @Test
    public void payCashValidTest() throws PaymentFailedException, InvalidModelException {
        int id = 0;
        String name = "Mikhail";
        int age = 20;
        Sex sex = Sex.MALE;
        Adult adult = new Adult(id, name, age, sex);
        adult.payCash(BigDecimal.valueOf(1.0));
        System.out.println(adult.getCashBalance());
        BigDecimal bigDecimal = BigDecimal.valueOf(999.0);
        assertTrue(bigDecimal.equals(adult.getCashBalance()));
    }

    @Test
    public void payCashFailedException() throws PaymentFailedException, InvalidModelException {
        exceptException(PaymentFailedException.class, "not enough cash balance: ");
        Adult adult = new Adult(0, "Mikhail", 20, Sex.MALE);
        adult.payCash(BigDecimal.valueOf(1001.0));
    }

    @Test
    public void payCardValidTest() throws PaymentFailedException, InvalidModelException {
        int id = 0;
        String name = "Mikhail";
        int age = 20;
        Sex sex = Sex.MALE;
        Adult adult = new Adult(id, name, age, sex);
        adult.payCard(BigDecimal.valueOf(1.0));
        System.out.println(adult.getCardBalance());
        BigDecimal bigDecimal = BigDecimal.valueOf(999.0);
        assertTrue(bigDecimal.equals(adult.getCardBalance()));
    }

    @Test
    public void payCardFailedException() throws PaymentFailedException, InvalidModelException {
        exceptException(PaymentFailedException.class, "not enough cash balance: ");
        Adult adult = new Adult(0, "Mikhail", 20, Sex.MALE);
        adult.payCard(BigDecimal.valueOf(1001.0));
    }

    @Test
    public void payBonusValidTest() throws PaymentFailedException, InvalidModelException {
        int id = 0;
        String name = "Mikhail";
        int age = 20;
        Sex sex = Sex.MALE;
        Adult adult = new Adult(id, name, age, sex);
        adult.payBonus(BigDecimal.valueOf(1.0));
        System.out.println(adult.getBonusBalance());
        BigDecimal bigDecimal = BigDecimal.valueOf(999.0);
        assertTrue(bigDecimal.equals(adult.getBonusBalance()));
    }


    @Test
    public void payBonusFailedException() throws PaymentFailedException, InvalidModelException {
        exceptException(PaymentFailedException.class, "not enough cash balance: ");
        Adult adult = new Adult(0, "Mikhail", 20, Sex.MALE);
        adult.payBonus(BigDecimal.valueOf(1001.0));
    }

    @Test
    public void addBonus() throws InvalidModelException {
        int id = 0;
        String name = "Mikhail";
        int age = 20;
        Sex sex = Sex.MALE;
        Adult adult = new Adult(id, name, age, sex);
        adult.addBonus(BigDecimal.valueOf(1.0));
        System.out.println(adult.getCashBalance());
        BigDecimal bigDecimal = BigDecimal.valueOf(1001.0);
        assertTrue(bigDecimal.equals(adult.getBonusBalance()));
    }
}