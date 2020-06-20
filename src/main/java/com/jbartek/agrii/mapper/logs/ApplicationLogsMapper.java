package com.jbartek.agrii.mapper.logs;

import com.jbartek.agrii.domain.logs.ApplicationLogs;
import com.jbartek.agrii.dto.logs.ApplicationLogsDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ApplicationLogsMapper {
    public ApplicationLogs mapToApplicationLogs(ApplicationLogsDto applicationLogsDto){
        return new ApplicationLogs(
                applicationLogsDto.getType(),
                applicationLogsDto.getName());
    }

    public ApplicationLogsDto mapToApplicationLogsDto(ApplicationLogs applicationLogs){
        return new ApplicationLogsDto(
                applicationLogs.getId(),
                applicationLogs.getType(),
                applicationLogs.getDate(),
                applicationLogs.getName());
    }

    public List<ApplicationLogsDto> mapToApplicationLogsDtoList(List<ApplicationLogs> applicationLogsList){
        return applicationLogsList.stream()
                .map(l-> new ApplicationLogsDto(
                        l.getId(), l.getType(),l.getDate(),l.getName()))
                .collect(Collectors.toList());
    }

    public List<ApplicationLogs> mapToApplicationLogs(List<ApplicationLogsDto> applicationLogsDtoList){
        return applicationLogsDtoList.stream()
                .map(l->new ApplicationLogs(
                        l.getType(),l.getName()))
                .collect(Collectors.toList());
    }
}
