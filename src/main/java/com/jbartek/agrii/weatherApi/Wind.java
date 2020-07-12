package com.jbartek.agrii.weatherApi;

public class Wind {
    private double speed;

    public double getSpeed() {
        return speed;
    }

    @Override
    public String toString() {
        return "Wind{" +
                "speed=" + speed +
                '}';
    }
}
