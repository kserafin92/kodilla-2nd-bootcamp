package com.kodilla.springevents.event;

import org.springframework.context.ApplicationEvent;

public class CalculationEvent extends ApplicationEvent {
    private final String operation;
    private final double operand1;
    private final double operand2;
    private final double result;

    public CalculationEvent(Object source, String operation, double operand1, double operand2, double result) {
        super(source);
        this.operation = operation;
        this.operand1 = operand1;
        this.operand2 = operand2;
        this.result = result;
    }

    public String getOperation() {
        return operation;
    }

    public double getOperand1() {
        return operand1;
    }

    public double getOperand2() {
        return operand2;
    }

    public double getResult() {
        return result;
    }
}
