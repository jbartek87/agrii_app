package com.jbartek.agrii.scheduler;


import com.jbartek.agrii.facade.logs.ApplicationLogsFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class LogScheduler {


    ApplicationLogsFacade facade;

    @Autowired
    public LogScheduler(ApplicationLogsFacade facade) {
        this.facade = facade;
    }

    @Scheduled(cron = "0 15 22 ? * MON-FRI")
    private void deleteLogs(){
       facade.clearLogs();
    }

}
