package com.jbartek.agrii.controller;

import com.jbartek.agrii.dto.FieldWorkDto;
import com.jbartek.agrii.exceptions.FieldWorkNotFoundException;
import com.jbartek.agrii.facade.FieldWorkFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin("*")
public class FieldWorkController {

    @Autowired
    FieldWorkFacade facade;

    @GetMapping(value = "/fieldWork")
    public List<FieldWorkDto> getFieldWorks(){
        return facade.fetchAllFieldWork();
    }

    @GetMapping(value = "/fieldWork/{fieldWorkId}")
    public FieldWorkDto getFieldWork(@PathVariable Long fieldWorkId) throws FieldWorkNotFoundException{
        return facade.fetchFieldWork(fieldWorkId).orElseThrow(FieldWorkNotFoundException::new);
    }

    @DeleteMapping(value = "/fieldWork/{fieldWorkId}")
    public void deleteFieldWork(@PathVariable Long fieldWorkId){
        facade.deleteFieldWork(fieldWorkId);
    }

    @PutMapping(value = "/fieldWork")
    public FieldWorkDto updateFieldWork(@RequestBody FieldWorkDto fieldWorkDto){
        return facade.updateFieldWork(fieldWorkDto);
    }

    @PutMapping(value = "/fieldWork")
    public void createField(FieldWorkDto fieldWorkDto){
        facade.createFieldWork(fieldWorkDto);
    }
}
