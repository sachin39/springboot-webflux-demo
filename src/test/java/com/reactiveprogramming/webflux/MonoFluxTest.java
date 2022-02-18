package com.reactiveprogramming.webflux;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {
    @Test
    public void testMono() {
        //Create a publisher
        Mono<String> stream1 = Mono.just("stream-1").log();

        //Subscriber subscribe to publisher
        stream1.subscribe(System.out::println);
    }

    @Test
    public void testMonoWithException() {
        //Create a publisher and manually throwing error
        Mono<?> stream1 = Mono.just("stream-1")
                .then(Mono.error(new RuntimeException("excepion test.")))
                .log();

        //Subscriber subscribe to publisher
        //subscribe is an overloaded method so we can also print exception
        stream1.subscribe(System.out::println, e -> System.out.println(e.getMessage()));
    }

    @Test
    public void testFlux() {
        //Create a publisher with number of data (stream) and log the events
        //there will be 4 onNext() calls because we have stream of 4 objects
        Flux<String> fluxString = Flux.just("Spring", "Spring boot", "Hibernate", "JPA")
                //concat one more string onNext calls will increase by 1
                .concatWithValues("AWS")
                .log();
        //after 5 onNext() calls we onComplete() method would be called upon successful completion
        //Subscriber subscribe to publisher
        fluxString.subscribe(System.out::println);
    }

    @Test
    public void testFluxWithException() {
        //Create a publisher with number of data (stream) and log the events
        //there will be 4 onNext() calls because we have stream of 4 objects
        Flux<String> fluxString = Flux.just("Spring", "Spring boot", "Hibernate", "JPA")
                //concat one more string onNext calls will increase by 1
                .concatWithValues("AWS")
                .concatWith(Flux.error(new RuntimeException("exception occurred in Flux.")))
                .concatWithValues("Cloud")
                .log();
        //after 5 onNext() calls we onError() method would be called upon Exception case.
        //Flux throws error so "Cloud" will not be concatenated to fluxString stream because an exception has been occurred.

        //Subscriber subscribe to publisher
        fluxString.subscribe(System.out::println, e -> System.out.print(e.getMessage()));
    }
}
