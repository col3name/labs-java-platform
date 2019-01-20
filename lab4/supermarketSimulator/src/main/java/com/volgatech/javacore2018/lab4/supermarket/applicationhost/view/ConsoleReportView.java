package com.volgatech.javacore2018.lab4.supermarket.applicationhost.view;

import com.volgatech.javacore2018.lab4.supermarket.domain.model.supermarket.Report;

import java.util.logging.Logger;

public class ConsoleReportView implements ReportView {
    private static final Logger LOG = Logger.getLogger(ConsoleReportView.class.getName());

    @Override
    public void print(final Report report) {
        String msg = "\n\n=======================\nReport:\n";
        LOG.info(msg);
        report.getReportStats().forEach((key, value) -> LOG.info(key + " : " + value));
    }
}
