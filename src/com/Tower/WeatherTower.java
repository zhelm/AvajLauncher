package com.Tower;

import com.Coordinates;
import com.WeatherProvider;

public class WeatherTower extends Tower {
   public String getWeather(Coordinates coordinates) {
       return WeatherProvider.getWeatherProvider().getWeather(coordinates);
   }
   

   public void changeWeather() {
       this.conditionsChanged();
   }

}