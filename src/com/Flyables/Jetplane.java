package com.Flyables;

import com.Coordinates;
import com.Tower.WeatherTower;

public class Jetplane extends Aircraft implements Flyable {
    
    protected Jetplane(String name, Coordinates coordinates) {
        super(name, coordinates);
        this.log(this, "Registered");
    }

    private WeatherTower weatherTower;
    private String currentWeather;

    public void updateConditions() {
        currentWeather = weatherTower.getWeather(coordinates);
        if(currentWeather == "SUN") {
            this.log(this,"Well this is nice");
            this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude() + 10, this.coordinates.getHeight() + 2);
        }
        // ◦ RAIN - Latitude increases with 5
        if(currentWeather == "RAIN") {
            this.log(this,"Well it couldnt possibly get any worse");
            this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude() + 5, this.coordinates.getHeight());
        }
        // ◦ FOG - Latitude increases with 1
        if(currentWeather == "FOG") {
            this.log(this,"Ahhhhhhh I cant see a thing");
            this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude() + 1, this.coordinates.getHeight());
        }
        // ◦ SNOW - Height decreases with 7
        if(currentWeather == "SNOW") {
            if(this.coordinates.getHeight() - 7 <= 0) {
                this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), this.coordinates.getHeight() - 7);
                land();
            } else {
                this.log(this,"Its getting real cold in this metal bird");
                this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), this.coordinates.getHeight() - 7);
            }
        }
        // this.coordinates = new Coordinates(123, 123, 123);
    }

    private void land() {
        this.log(this, "I need to land this Jetplane", true);
        unregisterTower();
    }

    private void unregisterTower() {
        weatherTower.unregister(this);
    }

    public void registerTower(WeatherTower weatherTower) {
        weatherTower.register(this);
        this.weatherTower = weatherTower;
    }

}