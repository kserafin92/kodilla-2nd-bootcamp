package com.kodilla.weather.proxy;

import com.kodilla.weather.WeatherForecast;
import com.kodilla.weather.error.ErrorHandler;

public class WeatherForecastProxy implements ErrorHandler {

    private WeatherForecast realWeatherForecast;

    @Override
    public void executeOperation() throws InterruptedException {
        if (realWeatherForecast == null) {
            try {
                realWeatherForecast = new WeatherForecast();
            } catch (InterruptedException e) {
                handleError(e);
            }
        }
    }

    @Override
    public void handleError(Exception e) {
        System.err.println("Error occurred in proxy: " + e.getMessage());
        e.printStackTrace();
    }

    public String getWeather() {
        if (realWeatherForecast == null) {
            try {
                realWeatherForecast = new WeatherForecast();
            } catch (InterruptedException e) {
                handleError(e);
                return "Unable to get weather";
            }
        }
        return realWeatherForecast.getWeather();
    }

    public void refreshData() {
        if (realWeatherForecast == null) {
            try {
                realWeatherForecast = new WeatherForecast();
            } catch (InterruptedException e) {
                handleError(e);
            }
        }
        try {
            realWeatherForecast.refreshData();
        } catch (InterruptedException e) {
            handleError(e);
        }
    }
}
