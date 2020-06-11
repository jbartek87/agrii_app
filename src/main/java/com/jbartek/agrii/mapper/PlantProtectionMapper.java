package com.jbartek.agrii.mapper;

import com.jbartek.agrii.domain.PlantProtection;
import com.jbartek.agrii.dto.PlantProtectionDto;
import com.jbartek.agrii.dto.WeatherDto;
import com.jbartek.agrii.services.ParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PlantProtectionMapper {

    @Autowired
    ParcelService parcelService;


    public PlantProtection mapToPlantProtection(final PlantProtectionDto plantProtectionDto) {
        return new PlantProtection(
                plantProtectionDto.getDateOfWork(),
                plantProtectionDto.getProductName(),
                plantProtectionDto.getProtectionType(),
                plantProtectionDto.getDose(),
                plantProtectionDto.getCultivatedPlant(),
                parcelService.getParcel(plantProtectionDto.getId()).orElse(null));
    }

    public PlantProtectionDto mapToPlantProtectionDto(final PlantProtection plantProtection) {
        return new PlantProtectionDto(
                plantProtection.getId(),
                plantProtection.getDateOfWork(),
                plantProtection.getProductName(),
                plantProtection.getProtectionType(),
                plantProtection.getDose(),
                plantProtection.getCultivatedPlant(),
                plantProtection.getParcel().getId());

    }

    public List<PlantProtectionDto> mapToPlantProtectionDtoList(final List<PlantProtection> plantProtectionList) {
        return plantProtectionList.stream()
                .map(p -> new PlantProtectionDto(
                        p.getId(),
                        p.getDateOfWork(),
                        p.getProductName(),
                        p.getProtectionType(),
                        p.getDose(),
                        p.getCultivatedPlant(),
                        p.getParcel().getId()))
                .collect(Collectors.toList());
    }

    public List<PlantProtection> mapToPlantProtectionList(final List<PlantProtectionDto> plantProtectionDtoList){
        return plantProtectionDtoList.stream()
                .map(p->new PlantProtection(
                        p.getDateOfWork(),
                        p.getProductName(),
                        p.getProtectionType(),
                        p.getDose(),
                        p.getCultivatedPlant(),
                        parcelService.getParcel(p.getId()).orElse(null)))
                .collect(Collectors.toList());
    }
}
