package com.jbartek.agrii.repository.logs;


import com.jbartek.agrii.domain.logs.ApplicationLogs;
import com.jbartek.agrii.enums.LogType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional
public interface ApplicationLogsRepository extends CrudRepository<ApplicationLogs, Long> {

    @Override
    List<ApplicationLogs> findAll();

    @Override
    long count();

    List<ApplicationLogs> findByDate(LocalDate date);
    List<ApplicationLogs> findByType(LogType type);

    ApplicationLogs save(ApplicationLogs log);

    @Override
    void deleteAll();

}
