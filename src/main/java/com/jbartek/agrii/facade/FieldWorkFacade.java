package com.jbartek.agrii.facade;


import com.jbartek.agrii.domain.FieldWork;
import com.jbartek.agrii.dto.FieldWorkDto;
import com.jbartek.agrii.mapper.FieldWorkMapper;
import com.jbartek.agrii.services.FieldWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FieldWorkFacade {

    @Autowired
    FieldWorkService service;

    @Autowired
    FieldWorkMapper mapper;

    public List<FieldWorkDto> fetchAllFieldWork(){
        return mapper.mapToFieldWorkDtoList(service.getAllFieldWork());
    }

    public Optional<FieldWorkDto> fetchFieldWork(Long fieldWorkId){
        return Optional.ofNullable(mapper.mapToFieldWorkDto(service.getFieldWork(fieldWorkId).orElse(null)));
    }

    public void deleteFieldWork(Long fieldWorkId){
        service.deleteFieldWork(fieldWorkId);
    }

    public FieldWorkDto updateFieldWork(FieldWorkDto fieldWorkDto){
        return mapper.mapToFieldWorkDto(service.saveFieldWork(mapper.mapToFieldWork(fieldWorkDto)));
    }

    public void createFieldWork(FieldWorkDto fieldWorkDto){
       service.saveFieldWork(mapper.mapToFieldWork(fieldWorkDto));
    }

    public List<FieldWorkDto> fetchFieldWorkByEmail(String email){
        return mapper.mapToFieldWorkDtoList(service.getFieldWorkByEmail(email));
    }
}
