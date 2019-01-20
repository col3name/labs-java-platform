package com.volgatech.javacore2018.lab4.supermarket.applicationhost.controller;

import com.java.lab.applicationservices.services.SupermarketServiceImpl;
import com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.services.SupermarketService;
import com.java.lab.domain.model.product.BaseProduct;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.supermarket.Report;
import com.volgatech.javacore2018.lab4.supermarket.applicationservices.services.SupermarketServiceImpl;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.product.BaseProduct;
import org.apache.log4j.Logger;

import java.util.Map;

public class SimulatorController {
    public static final Logger LOG = Logger.getLogger(SupermarketServiceImpl.class.getName());

    private SupermarketService supermarket;

    public SimulatorController(SupermarketService supermarketService) {
        this.supermarket = supermarketService;
    }

    public void execute() {
        Report report = supermarket.run();
        printReport(report.getReportStats());
    }

    private void printReport(Map<BaseProduct, Integer> reportStats) {
        LOG.info("Report:\n\n");
        for (Map.Entry<BaseProduct, Integer> reportEntry : reportStats.entrySet()) {
            LOG.info("{]" + reportEntry.getKey() + " : " + reportEntry.getValue());
        }
    }
}
