package com.volgatech.javacore2018.lab4.supermarket.applicationservices.services;

import com.volgatech.javacore2018.lab4.supermarket.domain.exception.PaymentFailedException;
import com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.repository.CustomerRepository;
import com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.services.BillService;
import com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.services.OrderConsumerService;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.customer.BaseCustomer;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.product.BaseProduct;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.supermarket.Order;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.supermarket.Report;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.supermarket.payment.PaymentStrategy;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.logging.Logger;

public class OrderConsumerServiceImpl implements OrderConsumerService {
    private static final Logger LOG = Logger.getLogger(OrderConsumerServiceImpl.class.getName());

    private final CustomerRepository customerRepository;
    private final Report report;

    public OrderConsumerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.report = new Report();
    }

    @Override
    public void payOrder(Order order, PaymentStrategy paymentStrategy) throws PaymentFailedException {
        Map<BaseProduct, Integer> productsInBasket = order.getBasket().getProducts();

        BaseCustomer customer = order.getCustomer();
        BillService service = new BillServiceImpl(report, productsInBasket, customer);
        BigDecimal totalPrice = service.computeOrderPrice();

        pay(customer, totalPrice, paymentStrategy);
    }

    @Override
    public Report getReport() {
        return report;
    }

    private void pay(BaseCustomer customer, BigDecimal totalPrice, PaymentStrategy paymentStrategy) throws PaymentFailedException {
        customerRepository.pay(customer, paymentStrategy, totalPrice);
        String msg = "[" + new Date() + "]" + customer + " payOrder " + totalPrice + " by " + paymentStrategy;
        LOG.info(msg);
    }
}
