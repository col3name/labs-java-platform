package com.java.lab.applicationservices.repository;

import com.java.lab.domain.exception.PaymentFailedException;
import com.java.lab.domain.exception.RepositoryException;
import com.java.lab.domain.interfaces.repository.CustomerRepository;
import com.java.lab.domain.model.customer.BaseCustomer;
import com.java.lab.domain.model.supermarket.payment.PaymentStrategy;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public class CustomerMemoryRepository implements CustomerRepository {
    private List<BaseCustomer> customers = new LinkedList<>();

    @Override
    public List<BaseCustomer> findAll() {
        return customers;
    }

    @Override
    public void add(BaseCustomer item) throws RepositoryException {
        if (isCustomerExist(item)) {
            throw new RepositoryException("The follow customer already exist: " + item.toString());
        }

        customers.add(item);
    }

    @Override
    public void update(BaseCustomer item) {
        int index = customers.indexOf(item);

        if (index != -1) {
            customers.set(index, item);
        }
    }

    @Override
    public void remove(BaseCustomer item) throws RepositoryException {
        if (!isCustomerExist(item)) {
            throw new RepositoryException("Unknown customer: " + item.toString());
        }

        customers.remove(item);
    }

    @Override
    public BaseCustomer find(Integer id) {
        return customers.get(id);
    }

    private boolean isCustomerExist(BaseCustomer item) {
        return customers.contains(item);
    }

    @Override
    public Integer size() {
        return customers.size();
    }

    @Override
    public void pay(BaseCustomer customer, PaymentStrategy strategy, BigDecimal price) throws PaymentFailedException {
        strategy.pay(customer, price);
    }
}
