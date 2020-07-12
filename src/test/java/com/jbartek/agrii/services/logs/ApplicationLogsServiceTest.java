package com.jbartek.agrii.services.logs;


import com.jbartek.agrii.domain.logs.ApplicationLogs;
import com.jbartek.agrii.enums.LogType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationLogsServiceTest {
    @Autowired
    ApplicationLogsService service;

    @Test
    public void testLogs(){
        //Given
        ApplicationLogs applicationLogs = new ApplicationLogs();
        applicationLogs.setId(1L);
        applicationLogs.setType(LogType.UPDATED);
        applicationLogs.setDate(LocalDate.of(2010,10,10));
        applicationLogs.setName("zapis");
        //When
        ApplicationLogs applicationLogTested = service.saveLog(applicationLogs);
        //Then
        Assert.assertEquals("zapis", applicationLogTested.getName());
        Assert.assertEquals(LocalDate.of(2010, 10, 10), applicationLogTested.getDate());
        service.deleteAll();
    }
}