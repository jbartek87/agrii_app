package com.jbartek.agrii.services.logs;

import com.jbartek.agrii.domain.logs.ApplicationLogs;
import com.jbartek.agrii.enums.LogType;
import com.jbartek.agrii.repository.logs.ApplicationLogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ApplicationLogsService {

    @Autowired
    ApplicationLogsRepository repository;

    public ApplicationLogs saveLog(ApplicationLogs applicationLogs){
        return repository.save(applicationLogs);
    }

    public void deleteAll(){
        repository.deleteAll();
    }


}
