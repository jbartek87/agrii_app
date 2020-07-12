package com.jbartek.agrii.weatherApi;


public class Main {

    private int temp;
    private int humidity;

    public int getTemp() {
        return temp;
    }

    public int getHumidity() {
        return humidity;
    }

    @Override
    public String toString() {
        return "Main{" +
                "temp=" + temp +
                ", humidity=" + humidity +
                '}';
    }
}
