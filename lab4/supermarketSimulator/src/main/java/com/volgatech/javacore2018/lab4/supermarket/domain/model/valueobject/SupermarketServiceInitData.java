package com.volgatech.javacore2018.lab4.supermarket.domain.model.valueobject;

import com.volgatech.javacore2018.lab4.supermarket.domain.exception.InvalidModelException;
import com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.services.OrderConsumerService;
import com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.services.OrderProducerService;

public class SupermarketServiceInitData {
    private final OrderProducerService orderProducerService;
    private final OrderConsumerService orderConsumerService;
    private Long timeout = 100L;
    private int countCashBox = 1;
    private int countOrderProducer = 1;

    public SupermarketServiceInitData(OrderProducerService orderProducerService, OrderConsumerService orderConsumerService) {
        this.orderProducerService = orderProducerService;
        this.orderConsumerService = orderConsumerService;
    }

    public Long getTimeout() {
        return timeout;
    }

    public SupermarketServiceInitData setTimeout(Long timeout) throws InvalidModelException {
        if (timeout <= 0L) {
            throw new InvalidModelException("Work millisecond must be more that 0");
        }

        this.timeout = timeout;
        return this;
    }

    public OrderProducerService getOrderProducerService() {
        return orderProducerService;
    }

    public OrderConsumerService getOrderConsumerService() {
        return orderConsumerService;
    }

    public int getCountCashBox() {
        return countCashBox;
    }

    public SupermarketServiceInitData setCountCashBox(int countCashBox) throws InvalidModelException {
        final int availableProcessors = Runtime.getRuntime().availableProcessors();
        if (countCashBox <= 1 && countCashBox > availableProcessors) {
            throw new InvalidModelException("count of cash box must be in range [" + 1 + ", " + availableProcessors + "]");
        }

        this.countCashBox = countCashBox;
        return this;
    }

    public int getCountOrderProducer() {
        return countOrderProducer;
    }

    public SupermarketServiceInitData setCountOrderProducer(int countOrderProducer) throws InvalidModelException {
        final int availableProcessors = Runtime.getRuntime().availableProcessors();
        if (countOrderProducer <= 1 && countOrderProducer > Runtime.getRuntime().availableProcessors()) {
            throw new InvalidModelException("Count of order producer must be in range [" + 1 + ", " + availableProcessors + "]");
        }

        this.countOrderProducer = countOrderProducer;
        return this;
    }


}
