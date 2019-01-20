package com.volgatech.javacore2018.lab4.supermarket.applicationhost.config;

import com.volgatech.javacore2018.lab4.supermarket.domain.exception.InvalidModelException;
import com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.services.OrderConsumerService;
import com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.services.OrderProducerService;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.valueobject.SupermarketServiceInitData;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class SupermarketInitDataBuilder {
    private final Properties properties;
    private SupermarketServiceInitData supermarketInitData;

    public SupermarketInitDataBuilder(OrderProducerService orderProducerService, OrderConsumerService orderConsumerService, String configFile) throws IOException {
        this.supermarketInitData = new SupermarketServiceInitData(orderProducerService, orderConsumerService);
        this.properties = new Properties();
        String rootPath = Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource("")).getPath();
        this.properties.load(new FileInputStream(rootPath + configFile));
    }

    public SupermarketServiceInitData build() throws InvalidModelException {
        Long timeOut = Long.valueOf(getProperty(properties, Config.WORK_MILLISECONDS_KEY, Config.WORK_TIME_DEFAULT_VALUE));
        int countOrderProducer = Integer.parseInt(getProperty(properties, Config.COUNT_OF_ORDER_PRODUCER, Config.COUNT_OF_ORDER_PRODUCER_DEFAULT_VALUE));
        int countCashBox = Integer.parseInt(getProperty(properties, Config.COUNT_CASH_BOX, Config.COUNT_CASH_BOX_PRODUCER_DEFAULT_VALUE));

        return supermarketInitData
                .setTimeout(timeOut)
                .setCountOrderProducer(countOrderProducer)
                .setCountCashBox(countCashBox);
    }

    private String getProperty(Properties properties, String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }
}
