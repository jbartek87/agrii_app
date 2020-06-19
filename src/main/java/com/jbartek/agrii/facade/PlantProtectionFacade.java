package com.jbartek.agrii.facade;

import com.jbartek.agrii.domain.PlantProtection;
import com.jbartek.agrii.dto.PlantProtectionDto;
import com.jbartek.agrii.mapper.PlantProtectionMapper;
import com.jbartek.agrii.services.PlantProtectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PlantProtectionFacade {
    @Autowired
    PlantProtectionService service;

    @Autowired
    PlantProtectionMapper mapper;

    public List<PlantProtectionDto> fetchAllPlantProtection(){
        return mapper.mapToPlantProtectionDtoList(service.getAllPlantProtection());
    }

    public Optional<PlantProtectionDto> fetchPlantProtection(Long plantProtectionId){
        return Optional.ofNullable(mapper.mapToPlantProtectionDto(service.getPlantProtection(plantProtectionId).orElse(null)));
    }

    public void deletePlantProtection(Long plantProtectionId){
        service.deletePlantProtection(plantProtectionId);
    }

    public PlantProtectionDto updatePlantProtection(PlantProtectionDto plantProtectionDto){
        return mapper.mapToPlantProtectionDto(service.savePlantProtection(mapper.mapToPlantProtection(plantProtectionDto)));
    }

    public void createPlantProtection(PlantProtectionDto plantProtectionDto) {
        service.savePlantProtection(mapper.mapToPlantProtection(plantProtectionDto));
    }

}
