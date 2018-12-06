package com.java.lab.domain.model.supermarket.payment;

import com.java.lab.domain.RandomPaymentCreator;
import com.java.lab.domain.exception.InvalidModelException;
import com.java.lab.domain.exception.PaymentFailedException;
import com.java.lab.domain.model.ExceptionTest;
import com.java.lab.domain.model.customer.Adult;
import com.java.lab.domain.model.customer.BaseCustomer;
import com.java.lab.domain.model.customer.Sex;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class PaymentStrategyTest extends ExceptionTest {
    @Test
    public void bonusPaymentStrategyTest() throws InvalidModelException, PaymentFailedException {
        BaseCustomer adult = new Adult(0, "Mikhail", 20, Sex.MALE);
        BigDecimal cashBalance = adult.getCashBalance();
        BigDecimal cardBalance = adult.getCardBalance();
        BigDecimal bonusBalance = adult.getBonusBalance();

        RandomPaymentCreator creator = new RandomPaymentCreator();
        PaymentStrategy paymentStrategy = creator.create(2);
        BigDecimal payAmount = BigDecimal.valueOf(10);
        paymentStrategy.pay(adult, payAmount);
        check(adult, cashBalance, cardBalance, bonusBalance.subtract(payAmount), paymentStrategy, "bonus payment");
    }

    @Test
    public void cashPaymentStrategyTest() throws InvalidModelException, PaymentFailedException {
        BaseCustomer adult = new Adult(0, "Mikhail", 20, Sex.MALE);
        BigDecimal cashBalance = adult.getCashBalance();
        BigDecimal cardBalance = adult.getCardBalance();
        BigDecimal bonusBalance = adult.getBonusBalance();

        RandomPaymentCreator creator = new RandomPaymentCreator();
        PaymentStrategy paymentStrategy = creator.create(1);
        BigDecimal payAmount = BigDecimal.valueOf(10);
        paymentStrategy.pay(adult, payAmount);
        check(adult, cashBalance.subtract(payAmount), cardBalance, bonusBalance, paymentStrategy, "cash payment");
    }

    @Test
    public void cardPaymentStrategyTest() throws InvalidModelException, PaymentFailedException {
        BaseCustomer adult = new Adult(0, "Mikhail", 20, Sex.MALE);
        BigDecimal cashBalance = adult.getCashBalance();
        BigDecimal cardBalance = adult.getCardBalance();
        BigDecimal bonusBalance = adult.getBonusBalance();

        RandomPaymentCreator creator = new RandomPaymentCreator();
        PaymentStrategy paymentStrategy = creator.create(0);
        BigDecimal payAmount = BigDecimal.valueOf(10);
        paymentStrategy.pay(adult, payAmount);
        check(adult, cashBalance, cardBalance.subtract(payAmount), bonusBalance, paymentStrategy, "card payment");
    }

    private void check(BaseCustomer adult, BigDecimal cashBalance, BigDecimal cardBalance, BigDecimal bonus, PaymentStrategy strategy, String str) {
        assertEquals(adult.getBonusBalance(), bonus);
        assertEquals(adult.getCardBalance(), cardBalance);
        assertEquals(adult.getCashBalance(), cashBalance);
        assertEquals(strategy.toString(), str);
    }
}