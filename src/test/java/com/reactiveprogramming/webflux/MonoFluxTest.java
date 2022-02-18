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
        stream1.subscribe(System.out::println, e-> System.out.println(e.getMessage()));
    }

    @Test
    public void testFlux(){
        //Create a publisher with number of data (stream)
        Flux<String> just = Flux.just("Spring", "Spring boot", "Hibernate", "JPA");

        //subscribe to publisher
    }
}
