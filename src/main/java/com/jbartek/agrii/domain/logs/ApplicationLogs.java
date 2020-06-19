package com.jbartek.agrii.domain.logs;


import com.jbartek.agrii.enums.LogType;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "APPLICATION_LOGS")
public class ApplicationLogs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "LOG_TYPE")
    private LogType type;

    @Column(name = "DATE")
    private LocalDate date;

    @Column(name = "INFORMATION")
    private String name;

    public ApplicationLogs(LogType type, String name) {
        this.type = type;
        this.date = LocalDate.now();
        this.name = name;
    }
}
