package com.volgatech.javacore2018.lab4.supermarket.applicationservices.services;

import com.volgatech.javacore2018.lab4.supermarket.applicationhost.controller.CashBox;
import com.volgatech.javacore2018.lab4.supermarket.applicationhost.controller.UserRunnable;
import com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.services.OrderConsumerService;
import com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.services.OrderProducerService;
import com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.services.SupermarketService;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.supermarket.Order;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.supermarket.Report;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.valueobject.SupermarketServiceInitData;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class SupermarketServiceImpl implements SupermarketService {
    private static final Logger LOG = Logger.getLogger(SupermarketServiceImpl.class.getName());
    private final int countCashBox;
    private final int countOrderProducer;
    private BlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();
    private Long timeOut;
    private OrderProducerService orderProducerService;
    private OrderConsumerService orderConsumerService;

    public SupermarketServiceImpl(SupermarketServiceInitData initData) {
        this.timeOut = initData.getTimeout();

        this.orderProducerService = initData.getOrderProducerService();
        this.orderConsumerService = initData.getOrderConsumerService();

        this.countOrderProducer = initData.getCountOrderProducer();
        this.countCashBox = initData.getCountCashBox();
    }

    @Override
    public Report run() {
        String msg = new Date() + " Supermarket opened";
        LOG.info(msg);

        long endTime = System.currentTimeMillis() + timeOut;

        UserRunnable userRunnable = new UserRunnable(orderQueue, orderProducerService, endTime);
        CashBox cashBox = new CashBox(orderQueue, orderConsumerService, endTime);

        ExecutorService orderThreadPool = Executors.newFixedThreadPool(countOrderProducer);
        List<Future> orderFutures = getFutures(countOrderProducer, userRunnable, orderThreadPool);
        ExecutorService cashBoxThreadPool = Executors.newFixedThreadPool(countCashBox);
        getFutures(countCashBox, cashBox, cashBoxThreadPool);

        try {
            Thread.sleep(timeOut);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOG.info(e.getMessage());
        }

        orderFutures.forEach(future -> {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                Thread.currentThread().interrupt();
                LOG.info(e.getMessage());
            }
        });

        orderThreadPool.shutdown();
        cashBoxThreadPool.shutdown();

        String msg1 = "[" + new Date() + "] Supermarket closed";
        LOG.info(msg1);

        return orderConsumerService.getReport();
    }

    private List<Future> getFutures(int nThreads, UserRunnable task, ExecutorService threadPool) {
        List<Future> futures = new LinkedList<>();

        IntStream.range(0, nThreads).forEach(i -> futures.add(threadPool.submit(task)));

        return futures;
    }

    private List<Future<Report>> getFutures(int nThreads, CashBox task, ExecutorService threadPool) {
        List<Future<Report>> futures = new LinkedList<>();

        IntStream.range(0, nThreads).forEach(i -> futures.add(threadPool.submit(task)));

        return futures;
    }
}
