package com.jbartek.agrii.weatherApi;


import com.jbartek.agrii.config.OwApiConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Controller
public class OpenWeather {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    OwApiConfig endpointConfig;

    public void getWeather(){
        final String url ="http://api.openweathermap.org/data/2.5/weather?q=Sulechow&appid=2c03ceb5d57407652d761e863d2e4da0&units=metric";
        ResponseEntity<WeatherResponse> response = restTemplate.getForEntity(url, WeatherResponse.class);

        final int temp = response.getBody().getMain().getTemp();
        final double wind = response.getBody().getWind().getSpeed();
        System.out.println("Temperature is " + temp + "|| Wind speed " + wind);

    }


}
