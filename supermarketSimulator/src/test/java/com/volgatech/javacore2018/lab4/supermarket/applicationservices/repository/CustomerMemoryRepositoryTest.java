package com.volgatech.javacore2018.lab4.supermarket.applicationservices.repository;

import com.volgatech.javacore2018.lab4.supermarket.domain.exception.InvalidModelException;
import com.volgatech.javacore2018.lab4.supermarket.domain.exception.PaymentFailedException;
import com.volgatech.javacore2018.lab4.supermarket.domain.exception.RepositoryException;
import com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.repository.CustomerRepository;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.ExceptionTest;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.customer.Adult;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.customer.BaseCustomer;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.customer.Sex;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.supermarket.payment.BonusPaymentStrategy;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CustomerMemoryRepositoryTest extends ExceptionTest {
    @Test
    public void addUnknownCustomerTest() throws InvalidModelException, RepositoryException {
        CustomerRepository repository = new CustomerMemoryRepository();
        Adult adult = new Adult(0, "Mikhail", 20, Sex.MALE);
        repository.add(adult);
        List<BaseCustomer> expectedCustomers = new ArrayList<>(1);
        expectedCustomers.add(adult);
        assertEquals(repository.findAll(), expectedCustomers);
    }

    @Test
    public void addExistCustomerTest() throws InvalidModelException, RepositoryException {
        exceptException(RepositoryException.class, "The follow customer already exist: ");

        CustomerRepository repository = new CustomerMemoryRepository();
        Adult adult = new Adult(0, "Mikhail", 20, Sex.MALE);
        repository.add(adult);
        repository.add(adult);
    }

    @Test
    public void findByIdTest() throws InvalidModelException, RepositoryException {
        CustomerRepository repository = new CustomerMemoryRepository();
        Adult adult = new Adult(0, "Mikhail", 20, Sex.MALE);
        repository.add(adult);
        BaseCustomer baseCustomer = repository.find(0);
        assertEquals(baseCustomer, adult);
    }

    @Test
    public void removeNotExistCustomerTest() throws InvalidModelException, RepositoryException {
        exceptException(RepositoryException.class, "Unknown customer: ");
        CustomerRepository repository = new CustomerMemoryRepository();
        Adult adult = new Adult(0, "Mikhail", 20, Sex.MALE);
        repository.remove(adult);
    }

    @Test
    public void removeExistCustomerTest() throws InvalidModelException, RepositoryException {
        CustomerRepository repository = new CustomerMemoryRepository();
        Adult adult = new Adult(0, "Mikhail", 20, Sex.MALE);
        repository.add(adult);
        repository.remove(adult);
        assertEquals(repository.size(), Integer.valueOf(0));
    }

    @Test
    public void updateCustomer() throws InvalidModelException, RepositoryException {
        CustomerRepository repository = new CustomerMemoryRepository();
        Adult adult1 = new Adult(0, "Mikhail", 20, Sex.MALE);
        Adult adult2 = new Adult(1, "Mikhail", 21, Sex.MALE);
        repository.add(adult1);
        repository.update(adult1);
        List<BaseCustomer> expectedCustomers = new ArrayList<>(1);
        expectedCustomers.add(adult1);
        assertEquals(repository.findAll(), expectedCustomers);
        assertEquals(repository.size(), Integer.valueOf(1));
    }

    @Test
    public void payTest() throws InvalidModelException, RepositoryException, PaymentFailedException {
        CustomerRepository repository = new CustomerMemoryRepository();
        Adult adult = new Adult(0, "Mikhail", 20, Sex.MALE, BigDecimal.valueOf(100.0), BigDecimal.valueOf(100.0), BigDecimal.valueOf(100.0));
        repository.add(adult);
        BigDecimal price = BigDecimal.valueOf(20);
        repository.pay(adult, new BonusPaymentStrategy(), price);

        Adult result = (Adult) repository.find(adult.getId());
        assertEquals(result.getBonusBalance(), BigDecimal.valueOf(80.));
        assertEquals(result.getCashBalance(), BigDecimal.valueOf(100.));
        assertEquals(result.getCardBalance(), BigDecimal.valueOf(100.));
    }
}