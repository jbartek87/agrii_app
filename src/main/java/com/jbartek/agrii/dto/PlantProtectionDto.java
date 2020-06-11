package com.jbartek.agrii.dto;

import com.jbartek.agrii.enums.ProtectionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PlantProtectionDto {
    private long id;
    private LocalDate dateOfWork;
    private String productName;
    private ProtectionType protectionType;
    private double dose;
    private String cultivatedPlant;
    private long parcelId;
}
