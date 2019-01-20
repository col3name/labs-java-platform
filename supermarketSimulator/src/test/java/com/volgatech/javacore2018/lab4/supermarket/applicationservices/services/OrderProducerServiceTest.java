package com.volgatech.javacore2018.lab4.supermarket.applicationservices.services;

import com.java.lab.applicationservices.initializer.CustomerMemoryInitializerImpl;
import com.java.lab.applicationservices.initializer.StoreMemoryInitializerImpl;
import com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.initialiazer.CustomerInitializer;
import com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.initialiazer.StoreInitializer;
import com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.repository.CustomerRepository;
import com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.repository.StoreRepository;
import com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.services.OrderProducerService;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.product.BaseProduct;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.supermarket.Order;
import com.volgatech.javacore2018.lab4.supermarket.applicationservices.initializer.CustomerMemoryInitializerImpl;
import com.volgatech.javacore2018.lab4.supermarket.applicationservices.initializer.StoreMemoryInitializerImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class OrderProducerServiceTest {

    private OrderProducerService producer;
    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;
    public static final int COUNT_PRODUCT_SELECTED = 3;
    private List<Integer> indexOfProducts;

    public OrderProducerServiceTest() {
        CustomerInitializer customerInitializer = new CustomerMemoryInitializerImpl();
        customerRepository = customerInitializer.init();
        StoreInitializer storeRepositoryInitializer = new StoreMemoryInitializerImpl();
        storeRepository = storeRepositoryInitializer.init();
        producer = new OrderProducerServiceImpl(customerRepository, storeRepository);
        indexOfProducts = chooseProductsIndex();
    }

    @Test
    public void test() {
        int customerIndex = 0;

        Order resultOrder = producer.produce(customerIndex, COUNT_PRODUCT_SELECTED, indexOfProducts);
        Order expectedOrder = new Order(customerRepository.find(customerIndex));

        selectOfProduct(indexOfProducts, expectedOrder);

        assertEquals(resultOrder, expectedOrder);
    }

    private List<Integer> chooseProductsIndex() {
        List<Integer> indexOfProducts = new ArrayList<>(COUNT_PRODUCT_SELECTED);
        indexOfProducts.addAll(Arrays.asList(0, 1, 2));
        return indexOfProducts;
    }

    private void selectOfProduct(List<Integer> indexOfProducts, Order expectedOrder) {
        for (Integer id : indexOfProducts) {
            Map.Entry<BaseProduct, Integer> productEntry = storeRepository.findById(id);
            expectedOrder.addProductToBasket(productEntry.getKey(), OrderProducerServiceTest.COUNT_PRODUCT_SELECTED);
        }
    }
}