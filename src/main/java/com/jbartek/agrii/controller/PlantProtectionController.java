package com.jbartek.agrii.controller;


import com.jbartek.agrii.domain.logs.ApplicationLogs;
import com.jbartek.agrii.dto.PlantProtectionDto;
import com.jbartek.agrii.enums.LogType;
import com.jbartek.agrii.exceptions.PlantProtectionException;
import com.jbartek.agrii.facade.PlantProtectionFacade;
import com.jbartek.agrii.services.logs.ApplicationLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin("*")
public class PlantProtectionController {

    @Autowired
    PlantProtectionFacade facade;

    @Autowired
    ApplicationLogsService service;

    @GetMapping(value = "/plantProtection")
    List<PlantProtectionDto> getAllPlantProtections() {
        return facade.fetchAllPlantProtection();
    }

    @GetMapping(value = "/plantProtectionEmail/{email}")
    List<PlantProtectionDto> getPlantProtectionByEmail(@PathVariable String email){
        return facade.fetchPlantProtectionByEmail(email);
    }

    @GetMapping(value = "/plantProtection/{id}")
    public PlantProtectionDto getPlantProtection(@PathVariable Long id) throws PlantProtectionException {
        return facade.fetchPlantProtection(id).orElseThrow(PlantProtectionException::new);
    }

    @DeleteMapping(value = "/plantProtection/{id}")
    public void deletePlantProtection(@PathVariable Long id) {
        PlantProtectionDto tempPlant = facade.fetchPlantProtection(id).orElse(null);
        if(tempPlant!=null){
            service.saveLog(new ApplicationLogs(LogType.DELETED, "Protection of" + tempPlant.getCultivatedPlant()
            + "was deleted"));
        }
        facade.deletePlantProtection(id);
    }

    @PostMapping(value = "/plantProtection")
    public void createPlantProtection(@RequestBody PlantProtectionDto plantProtectionDto) {
        facade.createPlantProtection(plantProtectionDto);
        service.saveLog(new ApplicationLogs(LogType.CREATED, "Protection of " +
                plantProtectionDto.getCultivatedPlant() + " was created"));
    }

    @PutMapping(value = "/plantProtection")
    public PlantProtectionDto updatePlantProtection(@RequestBody PlantProtectionDto plantProtectionDto) {
        PlantProtectionDto tempPlant = facade.updatePlantProtection(plantProtectionDto);
        if(tempPlant!=null){
            service.saveLog(new ApplicationLogs(LogType.UPDATED, "Protection of" +
                    plantProtectionDto.getCultivatedPlant() + " was updated"));
        }
        return facade.updatePlantProtection(plantProtectionDto);
    }
}
