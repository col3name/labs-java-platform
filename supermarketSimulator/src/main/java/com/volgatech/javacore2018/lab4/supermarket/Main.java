package com.volgatech.javacore2018.lab4.supermarket;

import com.volgatech.javacore2018.lab4.supermarket.applicationhost.controller.SimulatorController;
import com.java.lab.applicationservices.initializer.CustomerMemoryInitializerImpl;
import com.java.lab.applicationservices.initializer.StoreMemoryInitializerImpl;
import com.volgatech.javacore2018.lab4.supermarket.applicationservices.repository.CustomerMemoryRepository;
import com.volgatech.javacore2018.lab4.supermarket.applicationservices.repository.StoreMemoryRepositoryImpl;
import com.volgatech.javacore2018.lab4.supermarket.applicationservices.services.OrderConsumerServiceImpl;
import com.volgatech.javacore2018.lab4.supermarket.applicationservices.services.OrderProducerServiceImpl;
import com.volgatech.javacore2018.lab4.supermarket.applicationservices.services.SupermarketServiceImpl;
import com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.initialiazer.CustomerInitializer;
import com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.initialiazer.Initializer;
import com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.services.OrderConsumerService;
import com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.services.OrderProducerService;
import com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.services.SupermarketService;
import com.volgatech.javacore2018.lab4.supermarket.applicationservices.initializer.StoreMemoryInitializerImpl;

public class Main {
    public static void main(String[] args) {
        Initializer<StoreMemoryRepositoryImpl> storeRepositoryInitializer = new StoreMemoryInitializerImpl();
        StoreMemoryRepositoryImpl storeRepository = storeRepositoryInitializer.init();

        CustomerInitializer customerInitializer = new CustomerMemoryInitializerImpl();
        CustomerMemoryRepository customerRepository = (CustomerMemoryRepository) customerInitializer.init();

        Long timeOut = 100L;

        OrderProducerService producer = new OrderProducerServiceImpl(customerRepository, storeRepository);
        OrderConsumerService consumer = new OrderConsumerServiceImpl(customerRepository);
        SupermarketService supermarketService = new SupermarketServiceImpl(timeOut, producer, consumer);
        SimulatorController controller = new SimulatorController(supermarketService);
        controller.execute();
    }
}
