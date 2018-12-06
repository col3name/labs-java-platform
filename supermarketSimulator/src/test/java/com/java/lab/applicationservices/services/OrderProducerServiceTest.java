package com.java.lab.applicationservices.services;

import com.java.lab.applicationservices.initializer.CustomerMemoryInitializerImpl;
import com.java.lab.applicationservices.initializer.StoreMemoryInitializerImpl;
import com.java.lab.domain.interfaces.initialiazer.CustomerInitializer;
import com.java.lab.domain.interfaces.initialiazer.StoreInitializer;
import com.java.lab.domain.interfaces.repository.CustomerRepository;
import com.java.lab.domain.interfaces.repository.StoreRepository;
import com.java.lab.domain.interfaces.services.OrderProducerService;
import com.java.lab.domain.model.product.BaseProduct;
import com.java.lab.domain.model.supermarket.Order;
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