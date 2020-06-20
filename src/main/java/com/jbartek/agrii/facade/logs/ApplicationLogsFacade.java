package com.jbartek.agrii.facade.logs;

import com.jbartek.agrii.domain.logs.ApplicationLogs;
import com.jbartek.agrii.dto.logs.ApplicationLogsDto;
import com.jbartek.agrii.enums.LogType;
import com.jbartek.agrii.mapper.logs.ApplicationLogsMapper;
import com.jbartek.agrii.services.logs.ApplicationLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ApplicationLogsFacade {

    @Autowired
    ApplicationLogsMapper mapper;

    @Autowired
    ApplicationLogsService service;

    public List<ApplicationLogsDto> getAllLogs(){
        return mapper.mapToApplicationLogsDtoList(service.getAllLogs());
    }

    public long countLogs(){
        return service.countLogs();
    }

    public List<ApplicationLogsDto> findLogsByDate(LocalDate date){
        return mapper.mapToApplicationLogsDtoList(service.findLogByDate(date));
    }

    public List<ApplicationLogsDto> findLogsByType(LogType type){
        return mapper.mapToApplicationLogsDtoList(service.findLogByType(type));
    }

    public ApplicationLogsDto createApplicationLog(ApplicationLogsDto applicationLogsDto){
        return mapper.mapToApplicationLogsDto(service.saveLog(mapper.mapToApplicationLogs(applicationLogsDto)));
    }

    public void clearLogs(){
        service.deleteAll();
    }
}
