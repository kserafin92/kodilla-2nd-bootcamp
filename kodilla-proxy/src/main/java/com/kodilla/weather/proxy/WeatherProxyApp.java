package com.kodilla.weather.proxy;

import java.util.Random;

public class WeatherProxyApp {

    public static void main(String[] args) throws InterruptedException {
        long begin = System.currentTimeMillis();

        WeatherForecastProxy weatherForecast = new WeatherForecastProxy();
        for (int i = 0; i < 5; i++) {
            int randomValue = new Random().nextInt(100);
            if (randomValue < 20) {
                weatherForecast.refreshData();
            }
            System.out.println("Weather: " + weatherForecast.getWeather());
            System.out.println("Execution #" + i + " just finished");
        }

        long end = System.currentTimeMillis();
        System.out.println("The execution took: " + (end - begin) + " [ms]");
    }
}
