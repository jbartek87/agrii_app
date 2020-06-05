package com.jbartek.agrii.controller;


import com.jbartek.agrii.domain.Weather;
import com.jbartek.agrii.domain.WeatherDto;
import com.jbartek.agrii.exceptions.WeatherNotFoundException;
import com.jbartek.agrii.mapper.WeatherMapper;
import com.jbartek.agrii.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin("*")
public class WeatherController {
    @Autowired
    WeatherService service;

    @Autowired
    WeatherMapper mapper;

    @RequestMapping(method = RequestMethod.GET, value = "/weather")
    List<WeatherDto> getWeathers(){
        return mapper.mapToWeatherDtoList(service.getAllWeather());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/weather/{id}")
    public WeatherDto getWeather(@PathVariable Long id) throws WeatherNotFoundException {
        return mapper.mapToWeatherDto(service.getWeather(id).orElseThrow(WeatherNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/weather/{id}")
    public void deleteWeather(@PathVariable Long id){
        service.deleteWeather(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/weather")
    public void createWeather(@RequestBody WeatherDto weatherDto){
        service.saveWeather(mapper.mapToWeather(weatherDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/weather")
    public WeatherDto updateWeather(@RequestBody WeatherDto weatherDto){
        return mapper.mapToWeatherDto(service.saveWeather(mapper.mapToWeather(weatherDto)));
    }
}
