package com.volgatech.javacore2018.lab4.supermarket.applicationservices.services.useraction;

import com.volgatech.javacore2018.lab4.supermarket.applicationservices.services.SupermarketServiceImpl;
import com.volgatech.javacore2018.lab4.supermarket.domain.exception.NotFoundProductException;
import com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.repository.StoreRepository;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.customer.BaseCustomer;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.product.BaseProduct;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.supermarket.Order;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class OrderGeneratorImpl implements OrderGenerator {
    private static final Logger LOG = Logger.getLogger(SupermarketServiceImpl.class.getName());

    private final StoreRepository storeRepository;

    public OrderGeneratorImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public Order takeProducts(BaseCustomer customer, List<Integer> selectedProductIndex) {
        final int size = selectedProductIndex.size();
        Order order = new Order(customer);

        selectedProductIndex.forEach(index -> {
            try {
                BaseProduct selectedProduct = storeRepository.takeProduct(index, size);
                order.addProduct(selectedProduct, size);

                String msg = "[" + new Date() + "] Success take product" + customer + " add " + selectedProduct.toString() + " count: " + size;
                LOG.info(msg);
            } catch (NotFoundProductException e) {
                String msg = "[" + new Date() + "] Failed take product.";
                LOG.warning(msg);
            }
        });

        return order;
    }
}
