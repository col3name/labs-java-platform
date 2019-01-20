package com.volgatech.javacore2018.lab4.supermarket.applicationservices.services;

import com.java.lab.applicationhost.utill.RandomNumber;
import com.volgatech.javacore2018.lab4.supermarket.domain.RandomPaymentCreator;
import com.java.lab.domain.exception.PaymentFailedException;
import com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.services.OrderConsumerService;
import com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.services.OrderProducerService;
import com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.services.SupermarketService;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.supermarket.Order;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.supermarket.Report;
import com.volgatech.javacore2018.lab4.supermarket.applicationhost.utill.RandomNumber;
import com.volgatech.javacore2018.lab4.supermarket.domain.exception.PaymentFailedException;
import org.apache.log4j.Logger;

import java.util.*;

public class SupermarketServiceImpl implements SupermarketService {
    public static final Logger LOG = Logger.getLogger(SupermarketServiceImpl.class.getName());

    private Queue<Order> orderQueue = new LinkedList<>();

    private Long timeOut;
    private OrderProducerService producer;
    private OrderConsumerService consumer;

    public SupermarketServiceImpl(Long timeOut, OrderProducerService producer, OrderConsumerService consumer) {
        this.timeOut = timeOut;
        this.producer = producer;
        this.consumer = consumer;
    }

    @Override
    public Report run() {
        long end = System.currentTimeMillis() + timeOut;
        LOG.info(new Date() + " Supermarket opened");

        while (isOpened(end)) {
            Random random = new Random();

            produce(random.nextBoolean());
            buyProductOrder();
        }

        LOG.info("[" + new Date() + "] Supermarket closed");

        return consumer.getReport();
    }

    private boolean isOpened(long end) {
        return !orderQueue.isEmpty() || System.currentTimeMillis() < end;
    }

    private void produce(boolean isRequiredProduced) {
        if (isRequiredProduced) {
            int customerIndex = RandomNumber.getNumber(0, producer.customerRepositorySize() - 1);
            int countProductSelected = RandomNumber.getNumber(1, 3);
            List<Integer> indexOfProducts = generateIndexOfProducts(countProductSelected);

            orderQueue.add(producer.produce(customerIndex, countProductSelected, indexOfProducts));
        }
    }

    private void buyProductOrder() {
        if (!orderQueue.isEmpty()) {
            Order item = orderQueue.remove();
            try {
                RandomPaymentCreator creator = new RandomPaymentCreator();
                consumer.buyProductOrder(item, creator.create(RandomNumber.getNumber(3)));
            } catch (PaymentFailedException e) {
                LOG.info("[" + new Date() + "] failed pay order" + item);
            }
        }
    }

    private List<Integer> generateIndexOfProducts(int numberOfProduct) {
        ArrayList<Integer> indexOfProducts = new ArrayList<>(numberOfProduct);
        for (int i = 0; i < numberOfProduct; i++) {
            int index = RandomNumber.getNumber(0, producer.storeRepositorySize() - 1);

            indexOfProducts.add(index);
        }
        return indexOfProducts;
    }
}
