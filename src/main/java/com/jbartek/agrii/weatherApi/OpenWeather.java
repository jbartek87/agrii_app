package com.jbartek.agrii.weatherApi;


import com.jbartek.agrii.config.OwApiConfig;
import com.jbartek.agrii.dto.WeatherApiDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class OpenWeather {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    OwApiConfig endpointConfig;

    public String getWeather(String city){
        final String url ="http://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=2c03ceb5d57407652d761e863d2e4da0&units=metric";
        ResponseEntity<WeatherResponse> response = restTemplate.getForEntity(url, WeatherResponse.class);

        final int temp = response.getBody().getMain().getTemp();
        final double wind = response.getBody().getWind().getSpeed();
        final int humidity = response.getBody().getMain().getHumidity();

        String xxx = "Temperature is: " + temp + " celsius degrees\nWind speed is: " + wind +" m/s\n"+ "Humidity is: " + humidity;
        return xxx;

    }


}
