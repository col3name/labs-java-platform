package com.java.lab.applicationservices.services;

import com.java.lab.domain.exception.PaymentFailedException;
import com.java.lab.domain.interfaces.repository.CustomerRepository;
import com.java.lab.domain.interfaces.services.BillService;
import com.java.lab.domain.interfaces.services.OrderConsumerService;
import com.java.lab.domain.model.customer.BaseCustomer;
import com.java.lab.domain.model.product.BaseProduct;
import com.java.lab.domain.model.supermarket.Order;
import com.java.lab.domain.model.supermarket.Report;
import com.java.lab.domain.model.supermarket.payment.PaymentStrategy;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class OrderConsumerServiceImpl implements OrderConsumerService {
    public static final Logger LOG = Logger.getLogger(OrderConsumerServiceImpl.class.getName());

    private CustomerRepository customerRepository;
    private Report report;

    public OrderConsumerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
        this.report = new Report();
    }

    @Override
    public void buyProductOrder(Order order, PaymentStrategy paymentStrategy) throws PaymentFailedException {
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

    @Override
    public CustomerRepository getCustomerRepository() {
        return customerRepository;
    }

    private void pay(BaseCustomer customer, BigDecimal totalPrice, PaymentStrategy paymentStrategy) throws PaymentFailedException {
        customerRepository.pay(customer, paymentStrategy, totalPrice);
        LOG.info("[" + new Date() + "]" + customer + " pay " + totalPrice + " by " + paymentStrategy);
    }
}
