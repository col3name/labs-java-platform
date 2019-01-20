package com.volgatech.javacore2018.lab4.supermarket.applicationhost.controller;

import com.volgatech.javacore2018.lab4.supermarket.applicationhost.utill.RandomNumber;
import com.volgatech.javacore2018.lab4.supermarket.domain.RandomPaymentStrategyCreator;
import com.volgatech.javacore2018.lab4.supermarket.domain.exception.PaymentFailedException;
import com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.services.OrderConsumerService;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.supermarket.Order;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.supermarket.Report;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.supermarket.payment.PaymentStrategy;

import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

public class CashBox implements Callable<Report> {
    private static final Logger LOG = Logger.getLogger(CashBox.class.getName());
    private final long endTime;
    private BlockingQueue<Order> orderQueue;
    private OrderConsumerService consumer;

    public CashBox(BlockingQueue<Order> orderQueue, OrderConsumerService consumer, long endTime) {
        this.orderQueue = orderQueue;
        this.consumer = consumer;
        this.endTime = endTime;
    }

    @Override
    public Report call() {
        while (isOpened()) {
            work();
        }

        return consumer.getReport();
    }

    private void work() {
        Lock lock = new ReentrantLock();
        lock.lock();
        if (!orderQueue.isEmpty()) {

            Order order = orderQueue.remove();
            lock.unlock();

            if (order != null) {
                PaymentStrategy paymentStrategy = new RandomPaymentStrategyCreator().create(RandomNumber.getNumber(3));

                payOrder(order, paymentStrategy);
            }
        } else {
            lock.unlock();
        }
    }

    private boolean isOpened() {
        return !orderQueue.isEmpty() || System.currentTimeMillis() < endTime;
    }

    private void payOrder(Order order, PaymentStrategy paymentStrategy) {
        try {
            consumer.payOrder(order, paymentStrategy);
        } catch (PaymentFailedException e) {
            String msg = "[" + new Date() + "] failed payOrder order" + order;
            LOG.info(msg);
        }
    }
}
