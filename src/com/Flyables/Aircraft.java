package com.Flyables;

import java.io.FileWriter;
import java.io.IOException;

import com.Coordinates;

public abstract class Aircraft {
    protected long id;
    protected String name;
    protected Coordinates coordinates;

        private static long idCounter = 0;
        private static String output = "";

        protected Aircraft(String name, Coordinates coordinates) {
            this.name = name;
            this.coordinates = coordinates;
            id = idCounter;
            nextId();
        }

        private long nextId() {
            return Aircraft.idCounter++;
        }

        public void log(Object type, String string) {
            output = output+ "\n" + type.getClass().getSimpleName()+"#"+this.name+"("+this.id+"): "+ string ;
            // TYPE#NAME(UNIQUE_ID): SPECIFIC_MESSAGE.
            try {
                FileWriter myWriter = new FileWriter("simulation.txt");
                myWriter.write(output);
                myWriter.close();
              } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
              }
        }

        public void log(Object type, String string, boolean land) {
            output = output + "\n" + type.getClass().getSimpleName()+"#"+this.name+"("+this.id+"): "+ string + " My coordinates are: "+this.coordinates.getHeight()+ " " + this.coordinates.getLongitude()+ " " + this.coordinates.getLatitude()+ ". Deregistering from tower.";
            try {
                FileWriter myWriter = new FileWriter("simulation.txt");
                myWriter.write(output);
                myWriter.close();
              } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
              }
        }
    }


