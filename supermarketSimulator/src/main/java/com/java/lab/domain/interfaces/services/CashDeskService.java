package com.java.lab.domain.interfaces.services;

import com.java.lab.domain.exception.PaymentFailedException;
import com.java.lab.domain.model.supermarket.Report;

public interface CashDeskService {
    public void work() throws PaymentFailedException;

    public Report getReport();
}
