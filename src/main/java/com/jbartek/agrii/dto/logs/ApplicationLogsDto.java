package com.jbartek.agrii.dto.logs;


import com.jbartek.agrii.enums.LogType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ApplicationLogsDto {
    private long id;
    private LogType type;
    private LocalDate date;
    private String name;
}