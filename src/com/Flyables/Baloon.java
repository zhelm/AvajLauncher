package com.Flyables;

import com.Coordinates;
import com.Tower.WeatherTower;

public class Baloon extends Aircraft implements Flyable {

    protected Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
        this.log(this, "Registered");
    }

    private WeatherTower weatherTower;
    private String currentWeather;

    public void updateConditions() {
        currentWeather = weatherTower.getWeather(coordinates);

        // ◦ SUN - Longitude increases with 2, Height increases with 4
        if(currentWeather == "SUN") {
            this.log(this,"Well this is nice");
            this.coordinates = new Coordinates(this.coordinates.getLongitude() + 2, this.coordinates.getLatitude(), this.coordinates.getHeight() + 4);
        }
        // ◦ RAIN - Height decreases with 5
        if(currentWeather == "RAIN") {
            if(this.coordinates.getHeight() - 5 <= 0) {
                this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), this.coordinates.getHeight() - 5);
                land();
            } else {
                this.log(this,"Why did I want to do this today");
                this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), this.coordinates.getHeight() - 5);
            }
        }
        // ◦ FOG - Height decreases with 3
        if(currentWeather == "FOG") {
            if(this.coordinates.getHeight() - 3 <= 0) {
                this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), this.coordinates.getHeight() - 3);
                land();
            } else {
                this.log(this,"I really hope I dont lost");
                this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), this.coordinates.getHeight() - 3);
            }
        }
        // ◦ SNOW - Height decreases with 15
        if(currentWeather == "SNOW") {
            if((this.coordinates.getHeight() - 15) <= 0) {
                this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), this.coordinates.getHeight() - 15);
                land();
            } else {
                this.log(this,"I should have braught another jacket");
                this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), this.coordinates.getHeight() - 15);
            }
        }
    }

    private void land() {
        this.log(this, "I need to land this balloon", true);
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