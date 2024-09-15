package com.kodilla.kodillajms.receiver;

import com.kodilla.kodillajms.domain.Order;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class OrderReceiver {

    @JmsListener(destination = "order-queue")
    public void receiveOrder(Order order) {
        System.out.println("Received order: " + order);
    }
}
