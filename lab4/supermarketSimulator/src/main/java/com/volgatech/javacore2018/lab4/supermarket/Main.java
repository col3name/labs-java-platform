package com.volgatech.javacore2018.lab4.supermarket;

import com.volgatech.javacore2018.lab4.supermarket.applicationhost.controller.SimulatorController;
import com.volgatech.javacore2018.lab4.supermarket.applicationhost.initializer.CustomerMemoryInitializerImpl;
import com.volgatech.javacore2018.lab4.supermarket.applicationhost.initializer.StoreMemoryInitializerImpl;
import com.volgatech.javacore2018.lab4.supermarket.applicationhost.view.ConsoleReportView;
import com.volgatech.javacore2018.lab4.supermarket.applicationservices.repository.CustomerMemoryRepository;
import com.volgatech.javacore2018.lab4.supermarket.applicationservices.repository.StoreMemoryRepositoryImpl;
import com.volgatech.javacore2018.lab4.supermarket.applicationservices.services.OrderConsumerServiceImpl;
import com.volgatech.javacore2018.lab4.supermarket.applicationservices.services.OrderProducerServiceImpl;
import com.volgatech.javacore2018.lab4.supermarket.applicationservices.services.SupermarketServiceImpl;
import com.volgatech.javacore2018.lab4.supermarket.applicationservices.services.useraction.OrderGeneratorImpl;
import com.volgatech.javacore2018.lab4.supermarket.domain.exception.InvalidModelException;
import com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.initialiazer.CustomerInitializer;
import com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.initialiazer.Initializer;
import com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.services.OrderConsumerService;
import com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.services.OrderProducerService;
import com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.services.SupermarketService;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.valueobject.SupermarketServiceInitData;

import java.util.logging.Logger;

public class Main {
    public static final Logger LOG = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        try {
            if (args.length != 3) {
                throw new InvalidModelException("Invalid argument count\nUsage java -jar target/supermarket-1.1-SNAPSHOT.jar <workMilliseconds> <countOrderProducer> <countCashBox>");
            }
            Long timeOut = Long.valueOf(args[0]);
            int countOrderProducer = Integer.parseInt(args[1]);
            int countCashBox = Integer.parseInt(args[2]);

            Initializer<StoreMemoryRepositoryImpl> storeRepositoryInitializer = new StoreMemoryInitializerImpl();
            StoreMemoryRepositoryImpl storeRepository = storeRepositoryInitializer.init();

            CustomerInitializer customerInitializer = new CustomerMemoryInitializerImpl();
            CustomerMemoryRepository customerRepository = (CustomerMemoryRepository) customerInitializer.init();

            OrderProducerService producer = new OrderProducerServiceImpl(customerRepository, storeRepository, new OrderGeneratorImpl(storeRepository));
            OrderConsumerService consumer = new OrderConsumerServiceImpl(customerRepository);

            SupermarketServiceInitData supermarketInitData = new SupermarketServiceInitData(producer, consumer)
                    .setTimeout(timeOut)
                    .setCountOrderProducer(countOrderProducer)
                    .setCountCashBox(countCashBox);

            SupermarketService supermarketService = new SupermarketServiceImpl(supermarketInitData);

            SimulatorController controller = new SimulatorController(supermarketService);
            controller.execute(new ConsoleReportView());
        } catch (InvalidModelException e) {
            LOG.warning(e.getMessage());
        }
    }
}