package com.java.lab.applicationhost.controller;

import com.java.lab.applicationservices.services.SupermarketServiceImpl;
import com.java.lab.domain.interfaces.services.SupermarketService;
import com.java.lab.domain.model.product.BaseProduct;
import com.java.lab.domain.model.supermarket.Report;
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
