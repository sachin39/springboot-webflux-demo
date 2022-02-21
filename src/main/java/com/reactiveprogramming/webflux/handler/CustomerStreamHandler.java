package com.reactiveprogramming.webflux.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.reactiveprogramming.webflux.dao.CustomerDao;
import com.reactiveprogramming.webflux.dto.Customer;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerStreamHandler {

	@Autowired
	private CustomerDao customerDao;

	public Mono<ServerResponse> getCustomerStream(ServerRequest request) {
		Flux<Customer> customersStream = customerDao.getCustomersStream();
		return ServerResponse.ok()
				.contentType(MediaType.TEXT_EVENT_STREAM)
				.body(customersStream, Customer.class);

	}
}
