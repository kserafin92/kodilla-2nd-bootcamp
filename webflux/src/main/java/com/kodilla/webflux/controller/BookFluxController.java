package com.kodilla.webflux.controller;

import com.kodilla.webflux.BookDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
public class BookFluxController {

    @GetMapping(value = "/books", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<BookDto> getBooks() {
        return Flux.just(
                        new BookDto("Title1", "Author1", 2000),
                        new BookDto("Title2", "Author2", 2001),
                        new BookDto("Title3", "Author3", 2002),
                        new BookDto("Title4", "Author4", 2003)
                )
                .delayElements(Duration.ofSeconds(1))
                .log();
    }
}