package com.Flyables;

import com.Coordinates;

public class AircraftFactory {

    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
        Coordinates coordinates = new Coordinates(longitude, latitude, height);

        if(type.equals("JetPlane")) {
            return new Jetplane(name, coordinates);
        } else if (type.equals("Baloon")) {
            return new Baloon(name, coordinates);
        } else if (type.equals("Helicopter")) {
            return new Helicopter(name, coordinates);
        }
        System.out.println("Hello world");
        return null;
    }
}