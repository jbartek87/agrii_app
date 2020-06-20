package com.jbartek.agrii.controller;

import com.jbartek.agrii.domain.logs.ApplicationLogs;
import com.jbartek.agrii.dto.FieldWorkDto;
import com.jbartek.agrii.enums.LogType;
import com.jbartek.agrii.exceptions.FieldWorkNotFoundException;
import com.jbartek.agrii.facade.FieldWorkFacade;
import com.jbartek.agrii.services.logs.ApplicationLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin("*")
public class FieldWorkController {

    @Autowired
    FieldWorkFacade facade;

    @Autowired
    ApplicationLogsService service;

    @GetMapping(value = "/fieldWork")
    public List<FieldWorkDto> getFieldWorks() {
        return facade.fetchAllFieldWork();
    }

    @GetMapping(value = "/fieldWork/{id}")
    public FieldWorkDto getFieldWork(@PathVariable Long id) throws FieldWorkNotFoundException {
        return facade.fetchFieldWork(id).orElseThrow(FieldWorkNotFoundException::new);
    }

    @DeleteMapping(value = "/fieldWork/{id}")
    public void deleteFieldWork(@PathVariable Long id) {
        FieldWorkDto tempWork = facade.fetchFieldWork(id).orElse(null);
        if (tempWork != null) {
            service.saveLog(new ApplicationLogs(LogType.DELETED, "Fieldwork " + tempWork.getTypeOfWork() +
                    " was deleted"));
        }
        facade.deleteFieldWork(id);
    }

    @PutMapping(value = "/fieldWork")
    public FieldWorkDto updateFieldWork(@RequestBody FieldWorkDto fieldWorkDto) {
        {
            FieldWorkDto tempWork = facade.updateFieldWork(fieldWorkDto);
            if (tempWork != null) {
                service.saveLog(new ApplicationLogs(LogType.UPDATED, "Fieldwok " + tempWork.getId() +
                        " was updated"));
            }
        }
        return facade.updateFieldWork(fieldWorkDto);
    }

    @PostMapping(value = "/fieldWork")
    public void createField(FieldWorkDto fieldWorkDto) {
        service.saveLog(new ApplicationLogs(LogType.CREATED, "Fieldwork " + fieldWorkDto.getTypeOfWork() +
                " was created"));
        facade.createFieldWork(fieldWorkDto);
    }
}
