package com.java.lab.applicationservices.initializer;

import com.java.lab.applicationservices.repository.CustomerMemoryRepository;
import com.java.lab.applicationservices.services.BillServiceImpl;
import com.java.lab.domain.exception.InvalidModelException;
import com.java.lab.domain.exception.RepositoryException;
import com.java.lab.domain.interfaces.initialiazer.CustomerInitializer;
import com.java.lab.domain.interfaces.repository.CustomerRepository;
import com.java.lab.domain.model.customer.Adult;
import com.java.lab.domain.model.customer.Child;
import com.java.lab.domain.model.customer.Retired;
import com.java.lab.domain.model.customer.Sex;
import org.apache.log4j.Logger;

public class CustomerMemoryInitializerImpl implements CustomerInitializer {
    public static final Logger LOG = Logger.getLogger(BillServiceImpl.class.getName());

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
            LOG.warn(e.getMessage());
        }

        return customerRepository;
    }
}
