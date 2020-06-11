package com.jbartek.agrii.controller;


import com.jbartek.agrii.dto.WeatherDto;
import com.jbartek.agrii.exceptions.WeatherNotFoundException;
import com.jbartek.agrii.mapper.PlantProtectionMapper;
import com.jbartek.agrii.services.PlantProtectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin("*")
public class PlantProtectionController {
    @Autowired
    PlantProtectionService service;

    @Autowired
    PlantProtectionMapper mapper;

    @RequestMapping(method = RequestMethod.GET, value = "/weather")
    List<WeatherDto> getWeathers(){
        return mapper.mapToWeatherDtoList(service.getAllPlantProtection());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/weather/{id}")
    public WeatherDto getWeather(@PathVariable Long id) throws WeatherNotFoundException {
        return mapper.mapToWeatherDto(service.getPlantProtection(id).orElseThrow(WeatherNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/weather/{id}")
    public void deleteWeather(@PathVariable Long id){
        service.deletePlantProtection(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/weather")
    public void createWeather(@RequestBody WeatherDto weatherDto){
        service.savePlantProtection(mapper.mapToWeather(weatherDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/weather")
    public WeatherDto updateWeather(@RequestBody WeatherDto weatherDto){
        return mapper.mapToWeatherDto(service.savePlantProtection(mapper.mapToWeather(weatherDto)));
    }
}
