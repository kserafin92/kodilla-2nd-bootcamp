package com.kodilla.springevents.controller;

import com.kodilla.springevents.event.CalculationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/calculator")
public class CalculatorController implements ApplicationEventPublisherAware {
    private ApplicationEventPublisher publisher;

    @PostMapping("/add")
    public String add(@RequestParam double a, @RequestParam double b) {
        double result = a + b;
        System.out.println("Publishing event for addition");
        publisher.publishEvent(new CalculationEvent(this, "add", a, b, result));
        return "Result: " + result;
    }

    @PostMapping("/subtract")
    public String subtract(@RequestParam double a, @RequestParam double b) {
        double result = a - b;
        publisher.publishEvent(new CalculationEvent(this, "subtract", a, b, result));
        return "Result: " + result;
    }

    @PostMapping("/multiply")
    public String multiply(@RequestParam double a, @RequestParam double b) {
        double result = a * b;
        publisher.publishEvent(new CalculationEvent(this, "multiply", a, b, result));
        return "Result: " + result;
    }

    @PostMapping("/divide")
    public String divide(@RequestParam double a, @RequestParam double b) {
        if (b == 0) {
            return "Error: Division by zero";
        }
        double result = a / b;
        publisher.publishEvent(new CalculationEvent(this, "divide", a, b, result));
        return "Result: " + result;
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.publisher = applicationEventPublisher;
    }
}
