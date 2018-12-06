package com.java.lab;

import com.java.lab.applicationhost.controller.SimulatorController;
import com.java.lab.applicationservices.initializer.CustomerMemoryInitializerImpl;
import com.java.lab.applicationservices.initializer.StoreMemoryInitializerImpl;
import com.java.lab.applicationservices.repository.CustomerMemoryRepository;
import com.java.lab.applicationservices.repository.StoreMemoryRepositoryImpl;
import com.java.lab.applicationservices.services.OrderConsumerServiceImpl;
import com.java.lab.applicationservices.services.OrderProducerServiceImpl;
import com.java.lab.applicationservices.services.SupermarketServiceImpl;
import com.java.lab.domain.interfaces.initialiazer.CustomerInitializer;
import com.java.lab.domain.interfaces.initialiazer.Initializer;
import com.java.lab.domain.interfaces.services.OrderConsumerService;
import com.java.lab.domain.interfaces.services.OrderProducerService;
import com.java.lab.domain.interfaces.services.SupermarketService;

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
