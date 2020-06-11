package com.jbartek.agrii.mapper;

import com.jbartek.agrii.domain.PlantProtection;
import com.jbartek.agrii.dto.WeatherDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlantProtectionMapper {
    public PlantProtection mapToWeather(final WeatherDto weatherDto){
        return new PlantProtection(
                weatherDto.getId(),
                weatherDto.getCity(),
                weatherDto.getTemperature(),
                weatherDto.getWindSpeed(),
                weatherDto.isRain(),
                weatherDto.isWeatherOk());
    }

    public WeatherDto mapToWeatherDto(final PlantProtection plantProtection) {
        return new WeatherDto(
                plantProtection.getId(),
                plantProtection.getCity(),
                plantProtection.getTemperature(),
                plantProtection.getWindSpeed(),
                plantProtection.isRain(),
                plantProtection.isWeatherOk());
    }

    public List<WeatherDto> mapToWeatherDtoList(final List<PlantProtection> plantProtectionList){
        return plantProtectionList.stream()
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
