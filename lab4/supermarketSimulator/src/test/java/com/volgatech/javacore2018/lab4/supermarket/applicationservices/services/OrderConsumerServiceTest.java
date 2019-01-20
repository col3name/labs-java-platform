package com.volgatech.javacore2018.lab4.supermarket.applicationservices.services;

import com.volgatech.javacore2018.lab4.supermarket.applicationhost.initializer.CustomerMemoryInitializerImpl;
import com.volgatech.javacore2018.lab4.supermarket.applicationservices.repository.CustomerMemoryRepository;
import com.volgatech.javacore2018.lab4.supermarket.domain.exception.InvalidModelException;
import com.volgatech.javacore2018.lab4.supermarket.domain.exception.PaymentFailedException;
import com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.initialiazer.CustomerInitializer;
import com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.services.OrderConsumerService;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.customer.Adult;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.customer.BaseCustomer;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.customer.Sex;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.product.CountableProduct;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.supermarket.Order;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.supermarket.Report;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.supermarket.payment.BonusPaymentStrategy;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;

public class OrderConsumerServiceTest {
    private static final Logger LOG = Logger.getLogger(OrderConsumerServiceTest.class.getName());

    private static CountableProduct SSD = null;
    private static CountableProduct HDD = null;

    static {
        try {
            SSD = new CountableProduct(0, "SSD", Adult.MIN_AGE, BigDecimal.valueOf(190.0), BigDecimal.valueOf(10.0));
            HDD = new CountableProduct(2, "hdd", Adult.MIN_AGE, BigDecimal.valueOf(190.0), BigDecimal.valueOf(10.0));
        } catch (InvalidModelException e) {
            LOG.warning(e.getMessage());
        }
    }

    private OrderConsumerService consumer;
    private Order order;

    public OrderConsumerServiceTest() throws InvalidModelException {
        CustomerInitializer customerInitializer = new CustomerMemoryInitializerImpl();
        CustomerMemoryRepository customerRepository = (CustomerMemoryRepository) customerInitializer.init();

        consumer = new OrderConsumerServiceImpl(customerRepository);
        BaseCustomer adult = new Adult(0, "Mikhail", 20, Sex.MALE);
        order = new Order(adult);

        order.addProduct(SSD, 2);
        order.addProduct(HDD, 3);
    }

    @Test
    public void test() throws PaymentFailedException {
        BonusPaymentStrategy paymentStrategy = new BonusPaymentStrategy();
        consumer.payOrder(order, paymentStrategy);
        Report resultReport = consumer.getReport();

        check(resultReport);
    }

    private void check(Report resultReport) {
        Report expectedReport = new Report();
        expectedReport.addProduct(SSD, 2);
        expectedReport.addProduct(HDD, 3);

        assertEquals(resultReport, expectedReport);
    }
}