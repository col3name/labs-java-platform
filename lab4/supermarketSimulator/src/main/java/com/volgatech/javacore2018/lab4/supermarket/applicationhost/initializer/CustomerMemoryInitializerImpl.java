package com.volgatech.javacore2018.lab4.supermarket.applicationhost.initializer;

import com.volgatech.javacore2018.lab4.supermarket.applicationservices.repository.CustomerMemoryRepository;
import com.volgatech.javacore2018.lab4.supermarket.applicationservices.services.BillServiceImpl;
import com.volgatech.javacore2018.lab4.supermarket.domain.exception.InvalidModelException;
import com.volgatech.javacore2018.lab4.supermarket.domain.exception.RepositoryException;
import com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.initialiazer.CustomerInitializer;
import com.volgatech.javacore2018.lab4.supermarket.domain.interfaces.repository.CustomerRepository;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.customer.Adult;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.customer.Child;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.customer.Retired;
import com.volgatech.javacore2018.lab4.supermarket.domain.model.customer.Sex;

import java.util.logging.Logger;

public class CustomerMemoryInitializerImpl implements CustomerInitializer {
    private static final Logger LOG = Logger.getLogger(BillServiceImpl.class.getName());

    @Override
    public CustomerRepository init() {
        CustomerMemoryRepository customerRepository = new CustomerMemoryRepository();
        try {
            customerRepository.add(new Child(1, "Михаил", 10, Sex.MALE));
            customerRepository.add(new Adult(0, "Ваня", 45, Sex.MALE));
            customerRepository.add(new Adult(3, "Василий", 60, Sex.MALE));
            customerRepository.add(new Child(4, "Василия", 15, Sex.MALE));
            customerRepository.add(new Adult(5, "Аня", 21, Sex.MALE));
            customerRepository.add(new Retired(6, "Иван", 71, Sex.MALE));
            customerRepository.add(new Retired(7, "Иван", 58, Sex.FEMALE));
        } catch (RepositoryException | InvalidModelException e) {
            LOG.warning(e.getMessage());
        }

        return customerRepository;
    }
}
