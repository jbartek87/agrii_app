package com.jbartek.agrii.weatherApi;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class WeatherResponse {

    private Main main;
    private Wind wind;


}
