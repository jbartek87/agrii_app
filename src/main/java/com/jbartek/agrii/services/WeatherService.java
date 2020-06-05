package com.jbartek.agrii.services;

import com.jbartek.agrii.domain.Parcel;
import com.jbartek.agrii.domain.Weather;
import com.jbartek.agrii.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class WeatherService {

    @Autowired
    WeatherRepository repository;

    public List<Weather> getAllWeather(){
        return repository.findAll();
    }

    public Optional<Weather> getWeather(long id){
        return repository.findById(id);
    }

    public Weather saveWeather(final Weather weather){
        return repository.save(weather);
    }

    public void deleteWeather(final long id){
        repository.deleteById(id);
    }
}
