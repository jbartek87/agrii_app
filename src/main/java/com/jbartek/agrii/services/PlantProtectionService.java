package com.jbartek.agrii.services;

import com.jbartek.agrii.domain.PlantProtection;
import com.jbartek.agrii.repository.PlantProtectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PlantProtectionService {

    @Autowired
    PlantProtectionRepository repository;

    public List<PlantProtection> getAllPlantProtection(){
        return repository.findAll();
    }

    public Optional<PlantProtection> getPlantProtection(long id){
        return repository.findById(id);
    }

    public PlantProtection savePlantProtection(final PlantProtection plantProtection){
        return repository.save(plantProtection);
    }

    public void deletePlantProtection(final long id){
        repository.deleteById(id);
    }
}
