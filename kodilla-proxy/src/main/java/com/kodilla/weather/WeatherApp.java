package com.kodilla.weather;

import java.util.Random;

public class WeatherApp {

    public static void main(String[] args) throws InterruptedException {
        long begin = System.currentTimeMillis();

        for (int i = 0; i < 5; i++) {
            WeatherForecast weatherForecast = new WeatherForecast();
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
