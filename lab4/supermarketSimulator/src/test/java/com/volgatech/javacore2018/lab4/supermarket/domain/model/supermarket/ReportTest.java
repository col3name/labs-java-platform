package com.volgatech.javacore2018.lab4.supermarket.domain.model.supermarket;

import com.volgatech.javacore2018.lab4.supermarket.domain.exception.InvalidModelException;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.ExceptionTest;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.customer.Adult;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.product.BaseProduct;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.product.CountableProduct;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.product.ProductWeighted;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ReportTest extends ExceptionTest {
    private static final Logger LOG = Logger.getLogger(ReportTest.class.getName());

    private static CountableProduct product = null;

    static {
        try {
            product = new CountableProduct(1, "SSD", 10, BigDecimal.valueOf(90.0), BigDecimal.valueOf(10.0));
        } catch (InvalidModelException e) {
            LOG.info(e.getMessage());
        }
    }

    private Report report = new Report();

    public ReportTest() {
        report.addProduct(product, 10);
    }

    @Test
    public void compareWithThemselves() {
        assertEquals(report, report);
    }

    @Test
    public void compareWithNull() {
        assertNotEquals(null, product);
    }

    @Test
    public void compareWithOtherClass() {
        assertNotEquals(new Report(), product);
    }

    @Test
    public void compareWithOtherProduct() {
        Report report1 = new Report();
        assertNotEquals(report1, report);
    }

    @Test
    public void test() throws InvalidModelException {
        BaseProduct product = new ProductWeighted(1, "SSD", Adult.MIN_AGE, BigDecimal.valueOf(90.0), BigDecimal.valueOf(0.0));
        Report report = new Report();
        int count = 10;
        report.addProduct(product, count);
        Map<BaseProduct, Integer> expectedReport = new HashMap<>();
        expectedReport.put(product, count);
        assertEquals(report.getReportStats(), expectedReport);
        assertEquals("Report{reportStats={ProductWeighted{name='SSD', ageLimit=18, price=90.0, bonus=0.0, id=1}=10}}", report.toString());
    }

    @Test
    public void reportAlreadyContainOfProduct() throws InvalidModelException {
        BaseProduct product = new ProductWeighted(1, "SSD", Adult.MIN_AGE, BigDecimal.valueOf(90.0), BigDecimal.valueOf(0.0));
        Report report = new Report();
        int count = 10;
        report.addProduct(product, count);
        report.addProduct(product, count);
        Map<BaseProduct, Integer> expectedReport = new HashMap<>();
        expectedReport.put(product, count * 2);
        assertEquals(report.getReportStats(), expectedReport);
    }
}