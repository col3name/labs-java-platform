package com.java.lab.domain.model.supermarket;

import com.java.lab.domain.model.product.BaseProduct;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Report {
    private Map<BaseProduct, Integer> reportStats = new HashMap<>();

    public void addProduct(BaseProduct product, Integer count) {
        if (reportStats.containsKey(product)) {
            count += reportStats.get(product);
        }

        reportStats.put(product, count);
    }

    public Map<BaseProduct, Integer> getReportStats() {
        return reportStats;
    }

    @Override
    public String toString() {
        return "Report{" +
                "reportStats=" + reportStats +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Report report = (Report) o;
        return Objects.equals(reportStats, report.reportStats);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reportStats);
    }
}

