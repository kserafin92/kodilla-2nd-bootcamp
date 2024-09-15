package com.kodilla.kodillajms.controller;

import com.kodilla.kodillajms.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private JmsTemplate jmsTemplate;

    @PostMapping(path = "/send")
    public void sendOrder(@RequestBody Order order) {
        System.out.println("Sending order: " + order);
        jmsTemplate.convertAndSend("order-queue", order);
    }
}
