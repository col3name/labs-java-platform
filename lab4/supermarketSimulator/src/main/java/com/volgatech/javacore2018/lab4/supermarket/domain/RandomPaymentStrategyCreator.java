package com.volgatech.javacore2018.lab4.supermarket.domain;

import com.volgatech.javacore2018.lab4.supermarket.domain.model.supermarket.payment.BonusPaymentStrategy;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.supermarket.payment.CardPaymentStrategy;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.supermarket.payment.CashPaymentStrategy;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.supermarket.payment.PaymentStrategy;

public class RandomPaymentStrategyCreator {
    public PaymentStrategy create(Integer selector) {
        if (selector == 0) {
            return new CardPaymentStrategy();
        } else if (selector == 1) {
            return new CashPaymentStrategy();
        }

        return new BonusPaymentStrategy();
    }
}
