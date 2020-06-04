package com.jbartek.agrii.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WeatherDto {
    private String city;
    private double temperature;
    private double windSpeed;
    private boolean rain;
    private boolean isWeatherOk;
}
