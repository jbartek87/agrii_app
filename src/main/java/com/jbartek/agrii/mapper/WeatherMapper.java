package com.jbartek.agrii.mapper;

import com.jbartek.agrii.domain.Weather;
import com.jbartek.agrii.domain.WeatherDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WeatherMapper {
    public Weather mapToWeather(final WeatherDto weatherDto){
        return new Weather(
                weatherDto.getId(),
                weatherDto.getCity(),
                weatherDto.getTemperature(),
                weatherDto.getWindSpeed(),
                weatherDto.isRain(),
                weatherDto.isWeatherOk());
    }

    public WeatherDto mapToWeatherDto(final Weather weather) {
        return new WeatherDto(
                weather.getId(),
                weather.getCity(),
                weather.getTemperature(),
                weather.getWindSpeed(),
                weather.isRain(),
                weather.isWeatherOk());
    }

    public List<WeatherDto> mapToWeatherDtoList(final List<Weather> weatherList){
        return weatherList.stream()
                .map(w->new WeatherDto(
                        w.getId(),
                        w.getCity(),
                        w.getTemperature(),
                        w.getWindSpeed(),
                        w.isRain(),
                        w.isWeatherOk()))
                .collect(Collectors.toList());
    }
}
