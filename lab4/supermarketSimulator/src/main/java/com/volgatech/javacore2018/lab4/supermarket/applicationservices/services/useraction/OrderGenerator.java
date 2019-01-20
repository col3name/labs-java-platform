package com.volgatech.javacore2018.lab4.supermarket.applicationservices.services.useraction;

import com.volgatech.javacore2018.lab4.supermarket.domain.model.customer.BaseCustomer;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.supermarket.Order;

import java.util.List;

public interface OrderGenerator {
    Order takeProducts(BaseCustomer customerIndex, List<Integer> indexes);
}
