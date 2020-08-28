package com.Flyables;

import com.Coordinates;
import com.Tower.WeatherTower;

public class Helicopter extends Aircraft implements Flyable {
    
    protected Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
        this.log(this, "Registered");
    }

    private WeatherTower weatherTower;
    private String currentWeather;

    public void updateConditions() {
        currentWeather = weatherTower.getWeather(coordinates);
        
        // ◦ SUN - Longitude increases with 10, Height increases with 2
        if(currentWeather == "SUN") {
            this.log(this,"Well this is nice");
            this.coordinates = new Coordinates(this.coordinates.getLongitude() + 10, this.coordinates.getLatitude(), this.coordinates.getHeight() + 2);
        }
        // ◦ RAIN - Longitude increases with 5
        if(currentWeather == "RAIN") {
            this.log(this,"The propeller is acting like a umbrella");
            this.coordinates = new Coordinates(this.coordinates.getLongitude() + 5, this.coordinates.getLatitude(), this.coordinates.getHeight());
        }
        // ◦ FOG - Longitude increases with 1
        if(currentWeather == "FOG") {
            this.log(this,"I dont know if I am moving forwards or backwards with all this fog");
            this.coordinates = new Coordinates(this.coordinates.getLongitude() + 1, this.coordinates.getLatitude(), this.coordinates.getHeight());
        }
        // ◦ SNOW - Height decreases with 12
        if(currentWeather == "SNOW") {
            if(this.coordinates.getHeight() - 12 <= 0) {
                this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), this.coordinates.getHeight() - 12);
                land();
            } else {
                this.log(this,"I will never fly in this type of weather again");
                this.coordinates = new Coordinates(this.coordinates.getLongitude(), this.coordinates.getLatitude(), this.coordinates.getHeight() - 12);
            }
        }
    }
    
    private void land() {
        this.log(this, "I need to land this Helicopter", true);
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