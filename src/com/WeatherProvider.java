package com;

import java.util.Random;

public class WeatherProvider {

    private float result = 0;

    private static String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};


    public static WeatherProvider getWeatherProvider() {
        return new WeatherProvider();
    }


    public String getWeather(Coordinates coordinates) {
        if(coordinates.getHeight() >= 1000) {
           result = determineWeather(1) + result;
        }
        else if(coordinates.getHeight() >= 100) {
            result = determineWeather(2) + result;
        }
        else if(coordinates.getHeight() >= 10) {
            result = determineWeather(3) + result;
        }
        else if(coordinates.getHeight() >= 0) {
            result = determineWeather(4) + result;
        } else {
            //ERROR
        }

        if(coordinates.getLatitude() >= 1000) {
            result = determineWeather(4) + result;
        }
        else if(coordinates.getLatitude() >= 100) {
            result = determineWeather(3) + result;
        }
        else if(coordinates.getLatitude() >= 10) {
            result = determineWeather(2) + result;
        }
        else if(coordinates.getLatitude() >= 0) {
            result = determineWeather(1) + result;
        } else {
            //ERROR
        }

        if(coordinates.getLongitude() >= 1000) {
            result = determineWeather(4) + result;
        }
        else if(coordinates.getLongitude() >= 100) {
            result = determineWeather(3) + result;
        }
        else if(coordinates.getLongitude() >= 10) {
            result = determineWeather(2) + result;
        }
        else if(coordinates.getLongitude() >= 0) {
            result = determineWeather(1) + result;
        } else {
            //ERROR
        }
        return weather[(int)(result/3)];
    }
    
    private float determineWeather(int multiplier) {
        int i = 0;
        Random rn = new Random();
        float result = 0;
        while(i != multiplier) {
            result = rn.nextFloat() + result;
            i++;
        }
        return (result);
    }
}