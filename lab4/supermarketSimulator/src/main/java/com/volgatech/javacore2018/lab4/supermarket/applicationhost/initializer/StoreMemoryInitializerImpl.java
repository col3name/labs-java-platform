package com.volgatech.javacore2018.lab4.supermarket.applicationhost.initializer;

import com.volgatech.javacore2018.lab4.supermarket.applicationhost.utill.RandomNumber;
import com.volgatech.javacore2018.lab4.supermarket.applicationservices.repository.StoreMemoryRepositoryImpl;
import com.volgatech.javacore2018.lab4.supermarket.applicationservices.services.BillServiceImpl;
import com.volgatech.javacore2018.lab4.supermarket.domain.exception.InvalidModelException;
import com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.initialiazer.StoreInitializer;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.customer.Adult;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.customer.Child;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.product.CountableProduct;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.product.ProductWeighted;

import java.math.BigDecimal;
import java.util.logging.Logger;

public class StoreMemoryInitializerImpl implements StoreInitializer {
    private static final Logger LOG = Logger.getLogger(BillServiceImpl.class.getName());

    @Override
    public StoreMemoryRepositoryImpl init() {
        StoreMemoryRepositoryImpl storeRepository = new StoreMemoryRepositoryImpl();
        try {
            storeRepository.addProduct(new ProductWeighted(1, "SSD", Adult.MIN_AGE, BigDecimal.valueOf(910.0), BigDecimal.valueOf(0.0)), RandomNumber.getNumber(10000, 200000));
            storeRepository.addProduct(new CountableProduct(0, "Alcohol", Adult.MIN_AGE,
                    BigDecimal.valueOf(90.0), BigDecimal.valueOf(0.0)), RandomNumber.getNumber(10000, 200000));
            storeRepository.addProduct(new CountableProduct(2, "Mouse", Child.MIN_AGE,
                    BigDecimal.valueOf(80.0), BigDecimal.valueOf(0.0)), RandomNumber.getNumber(10000, 200000));
            storeRepository.addProduct(new CountableProduct(3, "Monitor", Child.MIN_AGE,
                    BigDecimal.valueOf(500.0), BigDecimal.valueOf(0.0)), RandomNumber.getNumber(10000, 200000));
            storeRepository.addProduct(new ProductWeighted(4, "Keyboard", Child.MIN_AGE,
                    BigDecimal.valueOf(90.0), BigDecimal.valueOf(1.0)), RandomNumber.getNumber(10000, 200000));
            storeRepository.addProduct(new ProductWeighted(5, "Mouse pad", Child.MIN_AGE,
                    BigDecimal.valueOf(40.0), BigDecimal.valueOf(0.0)), RandomNumber.getNumber(10000, 200000));
            storeRepository.addProduct(new ProductWeighted(6, "Headphone", Child.MIN_AGE,
                    BigDecimal.valueOf(100), BigDecimal.valueOf(1.0)), RandomNumber.getNumber(10000, 200000));
            storeRepository.addProduct(new ProductWeighted(7, "Door", Child.MIN_AGE,
                    BigDecimal.valueOf(200.0), BigDecimal.valueOf(1.0)), RandomNumber.getNumber(10000, 200000));
            storeRepository.addProduct(new ProductWeighted(8, "HDD", Child.MIN_AGE,
                    BigDecimal.valueOf(90.0), BigDecimal.valueOf(0.0)), RandomNumber.getNumber(10000, 200000));
        } catch (InvalidModelException e) {
            LOG.warning(e.getMessage());
        }
        return storeRepository;
    }
}
