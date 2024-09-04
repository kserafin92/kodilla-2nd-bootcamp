package com.kodilla.springevents.service;

import com.kodilla.springevents.event.CalculationEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class CalculationEventListener implements ApplicationListener<CalculationEvent> {
    private static final Logger logger = LoggerFactory.getLogger(CalculationEventListener.class);

    @Override
    public void onApplicationEvent(CalculationEvent event) {
        logger.info("Operation: {} | Operand 1: {} | Operand 2: {} | Result: {}",
                event.getOperation(), event.getOperand1(), event.getOperand2(), event.getResult());
    }
}
