package com.jbartek.agrii.facade.logs;

import com.jbartek.agrii.services.logs.ApplicationLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApplicationLogsFacade {


    @Autowired
    ApplicationLogsService service;



    public void clearLogs(){
        service.deleteAll();
    }
}
