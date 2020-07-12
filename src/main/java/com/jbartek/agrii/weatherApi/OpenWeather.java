package com.jbartek.agrii.weatherApi;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class OpenWeather {

    @Autowired
    RestTemplate restTemplate;


    @Value("${ow.api.endpoint}")
    String owEndpoint;

    @Value("${ow.api.key.metric}")
    String keyMetric;

    public String getWeather(String city){
        final String url =owEndpoint + city + keyMetric;
        ResponseEntity<WeatherResponse> response = restTemplate.getForEntity(url, WeatherResponse.class);

        final int temp = response.getBody().getMain().getTemp();
        final double wind = response.getBody().getWind().getSpeed();
        final int humidity = response.getBody().getMain().getHumidity();

        String temperatureInfo = "Temperature is: " + temp + " celsius degrees\nWind speed is: " + wind +" m/s\n"
                + "Humidity is: " + humidity;
        return temperatureInfo;

    }


}
