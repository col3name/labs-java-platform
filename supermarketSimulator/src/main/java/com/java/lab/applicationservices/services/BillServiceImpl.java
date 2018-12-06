package com.java.lab.applicationservices.services;

import com.java.lab.domain.interfaces.services.BillService;
import com.java.lab.domain.model.customer.BaseCustomer;
import com.java.lab.domain.model.customer.Retired;
import com.java.lab.domain.model.product.BaseProduct;
import com.java.lab.domain.model.supermarket.Report;
import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class BillServiceImpl implements BillService {
    public static final Logger LOG = Logger.getLogger(BillServiceImpl.class.getName());

    private Report report;
    private Map<BaseProduct, Integer> productsInBasket;
    private BaseCustomer customer;

    public BillServiceImpl(Report report, Map<BaseProduct, Integer> productsInBasket, BaseCustomer customer) {
        this.report = report;
        this.productsInBasket = productsInBasket;
        this.customer = customer;
    }

    @Override
    public BigDecimal computeOrderPrice() {
        BigDecimal totalOrderPrice = BigDecimal.valueOf(0);

        for (Map.Entry<BaseProduct, Integer> mapEntry : this.productsInBasket.entrySet()) {
            BaseProduct product = mapEntry.getKey();
            Integer count = mapEntry.getValue();

            totalOrderPrice = computeProductPrice(customer, totalOrderPrice, product, count);
        }

        return totalOrderPrice;
    }

    private BigDecimal computeProductPrice(BaseCustomer customer, BigDecimal totalOrderPrice, BaseProduct product, Integer count) {
        if (isAvailableAgeForProductPay(customer, product)) {
            LOG.info("[" + new Date() + "]. Failed add product because age less than product limit age");
        } else {
            BigDecimal productTotalPrice = getProductTotalPrice(product, count);

            productTotalPrice = tryApplyDiscount(customer, product, productTotalPrice);

            customer.addBonus(product.getBonus());
            totalOrderPrice = totalOrderPrice.add(productTotalPrice);

            report.addProduct(product, count);
            LOG.info(String.format("[%s] take product: %s, %d, customer: %s", new Date(), product, count, customer));
        }
        return totalOrderPrice;
    }

    private boolean isAvailableAgeForProductPay(BaseCustomer customer, BaseProduct product) {
        return customer.getAge() < product.getAgeLimit();
    }

    private BigDecimal getProductTotalPrice(BaseProduct product, Integer count) {
        return product.getPrice().multiply(BigDecimal.valueOf(count));
    }

    private BigDecimal tryApplyDiscount(BaseCustomer customer, BaseProduct product, BigDecimal productTotalPrice) {
        if (customer instanceof Retired) {

            productTotalPrice = productTotalPrice.multiply(BigDecimal.valueOf(0.9));

            LOG.info(String.format("[%s] apply bonus: 0.9, product%s ,  customer: %s", new Date(), product, customer));
        }

        return productTotalPrice;
    }
}
