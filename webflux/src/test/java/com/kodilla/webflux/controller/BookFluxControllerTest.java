package com.kodilla.webflux.controller;

import com.kodilla.webflux.BookDto;
import reactor.core.publisher.Flux;

import static org.junit.jupiter.api.Assertions.*;

class BookFluxControllerTest {


    public static void main(String[] args) {
        Flux<BookDto> bookFlux = Flux.just(
                        new BookDto("Title1", "Author1", 2000),
                        new BookDto("Title2", "Author2", 2001),
                        new BookDto("Title3", "Author3", 2002),
                        new BookDto("Title4", "Author4", 2003)
                )
                .concatWith(Flux.error(new RuntimeException("Test exception")))
                .onErrorResume(e -> {
                    System.out.println("Error occurred: " + e.getMessage());
                    return Flux.empty();
                });

        bookFlux.subscribe(System.out::println);
    }
}

