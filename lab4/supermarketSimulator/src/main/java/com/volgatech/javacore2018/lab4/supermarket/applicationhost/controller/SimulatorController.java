package com.volgatech.javacore2018.lab4.supermarket.applicationhost.controller;

import com.volgatech.javacore2018.lab4.supermarket.applicationhost.view.ReportView;
import com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.services.SupermarketService;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.supermarket.Report;

public class SimulatorController {
    private SupermarketService supermarket;

    public SimulatorController(SupermarketService supermarketService) {
        this.supermarket = supermarketService;
    }

    public void execute(ReportView reportView) {
        Report report = supermarket.run();
        reportView.print(report);
    }
}
