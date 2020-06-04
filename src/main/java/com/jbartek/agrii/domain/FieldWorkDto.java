package com.jbartek.agrii.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FieldWorkDto {
    private long id;
    private LocalDate dateOfWork;
    private Parcel parcel;
    private String cultivatedPlant;
    private String typeOfWork;
    private String comments;
}