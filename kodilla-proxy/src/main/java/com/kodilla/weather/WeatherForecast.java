package com.kodilla.weather;

import com.kodilla.weather.error.ErrorHandler;

public class WeatherForecast implements ErrorHandler {
    private String weather;

    public WeatherForecast() throws InterruptedException {
        executeOperation();
    }

    @Override
    public void executeOperation() throws InterruptedException {
        refreshData();
    }

    @Override
    public void handleError(Exception e) {
        System.err.println("Error occurred: " + e.getMessage());
        e.printStackTrace();
    }

    public String getWeather() {
        return weather;
    }

    public void refreshData() throws InterruptedException {
        try {
            Thread.sleep(5000);
            this.weather = "Sunny with 20°C";
            System.out.println("Weather data refreshed.");
        } catch (InterruptedException e) {
            handleError(e);
            throw e;
        }
    }
}
