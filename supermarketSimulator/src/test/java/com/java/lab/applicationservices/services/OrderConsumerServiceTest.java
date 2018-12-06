package com.java.lab.applicationservices.services;

import com.java.lab.applicationservices.initializer.CustomerMemoryInitializerImpl;
import com.java.lab.applicationservices.repository.CustomerMemoryRepository;
import com.java.lab.domain.exception.InvalidModelException;
import com.java.lab.domain.exception.PaymentFailedException;
import com.java.lab.domain.interfaces.initialiazer.CustomerInitializer;
import com.java.lab.domain.interfaces.services.OrderConsumerService;
import com.java.lab.domain.model.customer.Adult;
import com.java.lab.domain.model.customer.BaseCustomer;
import com.java.lab.domain.model.customer.Sex;
import com.java.lab.domain.model.product.CountableProduct;
import com.java.lab.domain.model.supermarket.Order;
import com.java.lab.domain.model.supermarket.Report;
import com.java.lab.domain.model.supermarket.payment.BonusPaymentStrategy;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class OrderConsumerServiceTest {
    public static CountableProduct SSD = null;
    public static CountableProduct HDD = null;

    static {
        try {
            SSD = new CountableProduct(0, "SSD", Adult.MIN_AGE, BigDecimal.valueOf(190.0), BigDecimal.valueOf(10.0));
            HDD = new CountableProduct(2, "hdd", Adult.MIN_AGE, BigDecimal.valueOf(190.0), BigDecimal.valueOf(10.0));
        } catch (InvalidModelException e) {
            e.printStackTrace();
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

        order.addProductToBasket(SSD, 2);
        order.addProductToBasket(HDD, 3);
    }

    @Test
    public void test() throws PaymentFailedException {
        BonusPaymentStrategy paymentStrategy = new BonusPaymentStrategy();
        consumer.buyProductOrder(order, paymentStrategy);
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