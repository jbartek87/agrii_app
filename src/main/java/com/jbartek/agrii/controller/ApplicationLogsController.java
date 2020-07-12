package com.jbartek.agrii.controller;

import com.jbartek.agrii.facade.logs.ApplicationLogsFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@CrossOrigin("*")
public class ApplicationLogsController {
    @Autowired
    ApplicationLogsFacade facade;

    @DeleteMapping(value = "/deleteLogs")
    public void deleteAllLogs() {
        facade.clearLogs();
    }
}
