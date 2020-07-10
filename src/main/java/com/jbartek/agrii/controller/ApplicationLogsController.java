package com.jbartek.agrii.controller;

import com.jbartek.agrii.domain.logs.ApplicationLogs;
import com.jbartek.agrii.dto.AccountancyDto;
import com.jbartek.agrii.enums.LogType;
import com.jbartek.agrii.facade.AccountancyFacade;
import com.jbartek.agrii.facade.logs.ApplicationLogsFacade;
import com.jbartek.agrii.services.logs.ApplicationLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
