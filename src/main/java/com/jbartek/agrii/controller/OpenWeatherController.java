package com.jbartek.agrii.controller;

import com.jbartek.agrii.weatherApi.OpenWeather;
import com.jbartek.agrii.weatherApi.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/v1/api/")
@CrossOrigin("*")
public class OpenWeatherController {
    @Autowired
    OpenWeather client;

    @GetMapping(value = "weather")
    public void getWeather() throws IOException {
        client.getWeather();
    }
}
