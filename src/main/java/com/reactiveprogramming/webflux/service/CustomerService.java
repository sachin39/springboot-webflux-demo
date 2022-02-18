package com.reactiveprogramming.webflux.service;

import com.reactiveprogramming.webflux.dao.CustomerDao;
import com.reactiveprogramming.webflux.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public List<Customer> getCustomers() {
        Instant start = Instant.now();
        List<Customer> customers = customerDao.getCustomers();
        Instant end = Instant.now();
        System.out.println("Total time taken in milliseconds: " + Duration.between(start, end).toMillis());
        return customers;
    }

    public Flux<Customer> getCustomersStream() {
        Instant start = Instant.now();
        Flux<Customer> customers = customerDao.getCustomersStream();
        Instant end = Instant.now();
        System.out.println("Total time taken in milliseconds in stream: " + Duration.between(start, end).toMillis());
        return customers;
    }
}
