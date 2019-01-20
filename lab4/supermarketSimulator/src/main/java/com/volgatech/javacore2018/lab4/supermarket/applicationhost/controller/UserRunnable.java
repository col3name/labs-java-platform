package com.volgatech.javacore2018.lab4.supermarket.applicationhost.controller;

import com.volgatech.javacore2018.lab4.supermarket.applicationhost.utill.RandomNumber;
import com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.services.OrderProducerService;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.supermarket.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class UserRunnable implements Runnable {
    private final long endTime;
    private BlockingQueue<Order> orderQueue;
    private OrderProducerService orderProducer;

    public UserRunnable(BlockingQueue<Order> orderQueue, OrderProducerService orderProducerService, long endTime) {
        this.orderQueue = orderQueue;
        this.orderProducer = orderProducerService;
        this.endTime = endTime;
    }

    @Override
    public void run() {
        while (System.currentTimeMillis() < endTime) {
            int customerIndex = RandomNumber.getNumber(0, orderProducer.customerRepositorySize() - 1);
            int countOfSelectedProduct = RandomNumber.getNumber(1, 3);
            List<Integer> indexOfProducts = generateIndexOfProducts(countOfSelectedProduct);

            orderQueue.add(orderProducer.produce(customerIndex, indexOfProducts));
        }
    }

    private List<Integer> generateIndexOfProducts(int countOfSelectedProduct) {
        ArrayList<Integer> productIndex = new ArrayList<>(countOfSelectedProduct);

        for (int i = 0; i < countOfSelectedProduct; i++) {
            int index = RandomNumber.getNumber(0, orderProducer.storeRepositorySize() - 1);

            productIndex.add(index);
        }

        return productIndex;
    }
}
