package com.reactiveprogramming.webflux.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.reactiveprogramming.webflux.dao.CustomerDao;
import com.reactiveprogramming.webflux.dto.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

	@Autowired
	private CustomerDao customerDao;

	public Mono<ServerResponse> loadCustomers(ServerRequest request) {
		Flux<Customer> customerList = customerDao.getCustomerList();
		return ServerResponse.ok().body(customerList, Customer.class);
	}

	public Mono<ServerResponse> findCustomer(ServerRequest request) {

		int customerId = Integer.valueOf(request.pathVariable("input"));
//		Mono<Customer> customerMono = customerDao.getCustomerList().filter(customer -> customer.getId() == customerId)
//				.take(1).single(); //This line of code will also work
		Mono<Customer> customerMono = customerDao.getCustomerList().filter(customer -> customer.getId() == customerId)
				.next();
		return ServerResponse.ok().body(customerMono, Customer.class);
	}

	public Mono<ServerResponse> saveCustomer(ServerRequest request) {

		Mono<Customer> bodyToMono = request.bodyToMono(Customer.class);
		Mono<String> saved = bodyToMono.map(customer -> customer.getId() + " : " + customer.getName());
		return ServerResponse.ok().body(saved, String.class);
	}

}
