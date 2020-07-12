package com.jbartek.agrii.dto;

import com.jbartek.agrii.enums.TypeOfWork;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FieldWorkDto {
    private long id;
    private LocalDate dateOfWork;
    private String cultivatedPlant;
    private TypeOfWork typeOfWork;
    private String comments;
    private long parcelId;
}