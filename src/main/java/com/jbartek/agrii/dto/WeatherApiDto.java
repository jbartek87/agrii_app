package com.jbartek.agrii.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherApiDto {
    private int temp;
    private double wind;
    private int humidity;
}
