package com.java.lab.applicationservices.services;

import com.java.lab.domain.exception.NotFoundProductException;
import com.java.lab.domain.interfaces.repository.CustomerRepository;
import com.java.lab.domain.interfaces.repository.StoreRepository;
import com.java.lab.domain.interfaces.services.OrderProducerService;
import com.java.lab.domain.model.customer.BaseCustomer;
import com.java.lab.domain.model.product.BaseProduct;
import com.java.lab.domain.model.supermarket.Order;
import org.apache.log4j.Logger;

import java.util.Date;
import java.util.List;

public class OrderProducerServiceImpl implements OrderProducerService {
    public static final Logger LOG = Logger.getLogger(OrderProducerServiceImpl.class.getName());
    private CustomerRepository customerRepository;
    private StoreRepository storeRepository;

    public OrderProducerServiceImpl(CustomerRepository customerRepository, StoreRepository storeRepository) {
        this.customerRepository = customerRepository;
        this.storeRepository = storeRepository;
    }

    @Override
    public Order produce(Integer customerIndex, int countProductSelected, List<Integer> indexOfProducts) {
        BaseCustomer customer = customerRepository.find(customerIndex);
        LOG.info("[" + new Date() + "] New customer ‘" + customer + "’ arrived");

        return orderGenerate(customer, countProductSelected, indexOfProducts);
    }

    @Override
    public Integer customerRepositorySize() {
        return customerRepository.size();
    }

    @Override
    public Integer storeRepositorySize() {
        return storeRepository.getProducts().size();
    }

    private Order orderGenerate(BaseCustomer customer, int countProductSelected, List<Integer> indexs) {
        Order order = new Order(customer);

        for (int j = 0; j < countProductSelected; j++) {

            takeProduct(customer, order, countProductSelected, indexs.get(j));
        }

        return order;
    }

    private void takeProduct(BaseCustomer customer, Order order, int countProductSelected, int index) {
        try {
            BaseProduct selectedProduct = storeRepository.removeProductCount(index, countProductSelected);
            order.addProductToBasket(selectedProduct, countProductSelected);

            LOG.info("[" + new Date() + "] success task product" + customer + " add " + selectedProduct.toString() + " count: " + countProductSelected);
        } catch (NotFoundProductException e) {
            LOG.warn("[" + new Date() + "]. failed take product.");
        }
    }
}
