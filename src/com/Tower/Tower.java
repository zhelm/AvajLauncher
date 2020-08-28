package com.Tower;

import java.util.ArrayList;

import com.Flyables.Flyable;

public abstract class Tower {
    // This will be list of aircraft
    private ArrayList<Flyable> observers = new ArrayList<Flyable>();//Zero or more instances

    public void register(Flyable flyable) {
        observers.add(flyable);
    }

    public void unregister(Flyable flyable) {
        observers.remove(flyable);
    }
    
    protected void conditionsChanged() {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).updateConditions();
        }    
    }
}