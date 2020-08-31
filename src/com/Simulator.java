package com;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.Flyables.AircraftFactory;
import com.Flyables.Flyable;
import com.Tower.WeatherTower;

public class Simulator {

    public static void main(String[] args) throws IOException {

        if(args.length == 0) {
            System.out.println("Please provide an input text file.");
            return ;
        }
        Pattern validType = Pattern.compile("^(.*)(.txt)$");
        Matcher match = validType.matcher(args[0]);
        if(!match.find()) {
            System.out.println("This is not a text file");
            return ;
        }
        File check = new File(args[0]);
        if(!check.exists()) {
            System.out.println("File does not exist. Please check the path and try again.");
            return ;
        }
        if(!check.isFile()) {
            System.out.println("Please provide a valid file and not a directory.");
            return ;
        }

        List<String> read = Collections.emptyList();
        read = Files.readAllLines(Paths.get(args[0]));

        String[] lines = new String[read.size()];
        lines = read.toArray(lines);

        for (int i = 0; i < lines.length; i++) {
            Pattern pattern = Pattern.compile("^(Baloon|JetPlane|Helicopter) [A-Z]+[0-9]+ [0-9]+ [0-9]+ [0-9]+\\n?$");
            if(i == 0) {
                pattern = Pattern.compile("^[0-9]+\\n?$");
            }
            Matcher matcher = pattern.matcher(lines[i]);
            boolean matchFound = matcher.find();
            if(!matchFound) {
                System.err.println("ERROR: File has incorrect content");
                return ;
            } 
        }

        WeatherTower weatherTower = new WeatherTower();
        for (int i = 1; i < lines.length; i++) {
            String[] splitLines = lines[i].split(" ");
            Flyable aircraft = AircraftFactory.newAircraft(splitLines[0], splitLines[1], Integer.parseInt(splitLines[2]),  Integer.parseInt(splitLines[3]),  Integer.parseInt(splitLines[4])); 
            aircraft.registerTower(weatherTower);
        }
        for (int i = 0; i < Integer.parseInt(lines[0]); i++) {
            weatherTower.changeWeather();
        }
    }
}