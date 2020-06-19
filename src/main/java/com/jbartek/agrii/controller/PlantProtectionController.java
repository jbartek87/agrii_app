package com.jbartek.agrii.controller;


import com.jbartek.agrii.domain.PlantProtection;
import com.jbartek.agrii.dto.PlantProtectionDto;
import com.jbartek.agrii.exceptions.PlantProtectionException;
import com.jbartek.agrii.facade.PlantProtectionFacade;
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
    PlantProtectionFacade facade;

    @GetMapping(value = "/plantProtection")
    List<PlantProtectionDto> getAllPlantProtections() {
        return facade.fetchAllPlantProtection();
    }

    @GetMapping(value = "/plantProtection/{id}")
    public PlantProtectionDto getPlantProtection(@PathVariable Long id) throws PlantProtectionException {
        return facade.fetchPlantProtection(id).orElseThrow(PlantProtectionException::new);
    }

    @DeleteMapping(value = "/plantProtection/{id}")
    public void deletePlantProtection(@PathVariable Long id) {
        facade.deletePlantProtection(id);
    }

    @PostMapping(value = "/plantProtection")
    public void createPlantProtection(@RequestBody PlantProtectionDto plantProtectionDto) {
        facade.createPlantProtection(plantProtectionDto);
    }

    @PutMapping(value = "/plantProtection")
    public PlantProtectionDto updatePlantProtection(@RequestBody PlantProtectionDto plantProtectionDto) {
        return facade.updatePlantProtection(plantProtectionDto);
    }
}
