package com.java.lab.domain;

import com.java.lab.domain.model.supermarket.payment.BonusPaymentStrategy;
import com.java.lab.domain.model.supermarket.payment.CardPaymentStrategy;
import com.java.lab.domain.model.supermarket.payment.CashPaymentStrategy;
import com.java.lab.domain.model.supermarket.payment.PaymentStrategy;

public class RandomPaymentCreator {
    public PaymentStrategy create(Integer selector) {
        if (selector == 0) {
            return new CardPaymentStrategy();
        } else if (selector == 1) {
            return new CashPaymentStrategy();
        }

        return new BonusPaymentStrategy();
    }
}
