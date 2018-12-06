package com.java.lab.applicationservices.initializer;

import com.java.lab.applicationhost.utill.RandomNumber;
import com.java.lab.applicationservices.repository.StoreMemoryRepositoryImpl;
import com.java.lab.applicationservices.services.BillServiceImpl;
import com.java.lab.domain.exception.InvalidModelException;
import com.java.lab.domain.interfaces.initialiazer.StoreInitializer;
import com.java.lab.domain.model.customer.Adult;
import com.java.lab.domain.model.customer.Child;
import com.java.lab.domain.model.product.CountableProduct;
import com.java.lab.domain.model.product.ProductWeighted;
import org.apache.log4j.Logger;

import java.math.BigDecimal;

public class StoreMemoryInitializerImpl implements StoreInitializer {
    public static final Logger LOG = Logger.getLogger(BillServiceImpl.class.getName());

    @Override
    public StoreMemoryRepositoryImpl init() {
        StoreMemoryRepositoryImpl storeRepository = new StoreMemoryRepositoryImpl();
        try {
            storeRepository.addProduct(new ProductWeighted(0, "SSD", Adult.MIN_AGE, BigDecimal.valueOf(910.0),
                    BigDecimal.valueOf(0.0)), RandomNumber.getNumber(10000, 200000));
            storeRepository.addProduct(new CountableProduct(1, "Алкоголь", Adult.MIN_AGE,
                    BigDecimal.valueOf(90.0), BigDecimal.valueOf(0.0)), RandomNumber.getNumber(10000, 200000));
            storeRepository.addProduct(new CountableProduct(2, "Мышка", Child.MIN_AGE,
                    BigDecimal.valueOf(80.0), BigDecimal.valueOf(0.0)), RandomNumber.getNumber(10000, 200000));
            storeRepository.addProduct(new CountableProduct(3, "Монитор", Child.MIN_AGE,
                    BigDecimal.valueOf(500.0), BigDecimal.valueOf(0.0)), RandomNumber.getNumber(10000, 200000));
            storeRepository.addProduct(new ProductWeighted(4, "Клавиатура", Child.MIN_AGE,
                    BigDecimal.valueOf(90.0), BigDecimal.valueOf(1.0)), RandomNumber.getNumber(10000, 200000));
            storeRepository.addProduct(new ProductWeighted(5, "Кофрик для мышки", Child.MIN_AGE,
                    BigDecimal.valueOf(40.0), BigDecimal.valueOf(0.0)), RandomNumber.getNumber(10000, 200000));
            storeRepository.addProduct(new ProductWeighted(6, "Наушник", Child.MIN_AGE,
                    BigDecimal.valueOf(100), BigDecimal.valueOf(1.0)), RandomNumber.getNumber(10000, 200000));
            storeRepository.addProduct(new ProductWeighted(7, "SSD", Child.MIN_AGE,
                    BigDecimal.valueOf(200.0), BigDecimal.valueOf(1.0)), RandomNumber.getNumber(10000, 200000));
            storeRepository.addProduct(new ProductWeighted(8, "HDD", Child.MIN_AGE,
                    BigDecimal.valueOf(90.0), BigDecimal.valueOf(0.0)), RandomNumber.getNumber(10000, 200000));
        } catch (InvalidModelException e) {
            LOG.warn(e.getMessage());
        }
        return storeRepository;
    }
}
