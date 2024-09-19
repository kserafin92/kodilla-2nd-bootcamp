package com.kodilla.weather.error;

public interface ErrorHandler {

    void executeOperation() throws InterruptedException;

    void handleError(Exception e);
}
