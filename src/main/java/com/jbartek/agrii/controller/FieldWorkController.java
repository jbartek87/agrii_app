package com.jbartek.agrii.controller;

import com.jbartek.agrii.domain.FieldWork;
import com.jbartek.agrii.domain.FieldWorkDto;
import com.jbartek.agrii.domain.ParcelDto;
import com.jbartek.agrii.exceptions.FieldWorkNotFoundException;
import com.jbartek.agrii.mapper.FieldWorkMapper;
import com.jbartek.agrii.repository.FieldWorkRepository;
import com.jbartek.agrii.services.FieldWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.reflect.generics.repository.FieldRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin("*")
public class FieldWorkController {

    @Autowired
    FieldWorkMapper mapper;

    @Autowired
    FieldWorkService service;

    @RequestMapping(method = RequestMethod.GET, value = "/fieldwork")
    public List<FieldWorkDto> getAllFieldWork(){
        return mapper.mapToFieldWorkDtoList(service.getAllFieldWork());
    }

    @RequestMapping(method = RequestMethod.GET, value ="/fieldwork/{id}")
    public FieldWorkDto getFieldWork(@PathVariable Long id) throws FieldWorkNotFoundException {
       return mapper.mapToFieldWorkDto(service.getFieldWork(id).orElseThrow(FieldWorkNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/fieldwork")
    public FieldWorkDto updateFieldWork(@RequestBody FieldWorkDto fieldWorkDto){
        return mapper.mapToFieldWorkDto(service.saveFieldWork(mapper.mapToFieldWork(fieldWorkDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/fieldwork/{id}")
    public void deleteFieldWork(@PathVariable Long id){
        service.deleteFieldWork(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/fieldwork")
    public void createFieldWork(@RequestBody FieldWorkDto fieldWorkDto){
        service.saveFieldWork(mapper.mapToFieldWork(fieldWorkDto));
    }
}
