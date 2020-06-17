package com.jbartek.agrii.controller;


import com.jbartek.agrii.dto.PlantProtectionDto;
import com.jbartek.agrii.exceptions.PlantProtectionException;
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

    @RequestMapping(method = RequestMethod.GET, value = "/plantProtection")
    List<PlantProtectionDto> getAllPlantProtections(){
        return mapper.mapToPlantProtectionDtoList(service.getAllPlantProtection());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/plantProtection/{id}")
    public PlantProtectionDto getPlantProtection(@PathVariable Long id) throws PlantProtectionException {
        return mapper.mapToPlantProtectionDto(service.getPlantProtection(id).orElseThrow(PlantProtectionException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/plantProtection/{id}")
    public void deletePlantProtection(@PathVariable Long id){
        service.deletePlantProtection(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/plantProtection")
    public void createPlantProtection(@RequestBody PlantProtectionDto plantProtectionDto){
        service.savePlantProtection(mapper.mapToPlantProtection(plantProtectionDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/plantProtection")
    public PlantProtectionDto updatePlantProtection(@RequestBody PlantProtectionDto plantProtectionDto){
        return mapper.mapToPlantProtectionDto(service.savePlantProtection(mapper.mapToPlantProtection(plantProtectionDto)));
    }
}
