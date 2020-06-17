package com.jbartek.agrii.dto;

import com.jbartek.agrii.domain.PlantProtection;
import com.jbartek.agrii.enums.SoilType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ParcelDto {
    private long id;
    private String parcelNumber;
    private String precinct;
    private SoilType soilType;
    private double area;
    private List<FieldWorkDto> fieldWorkDtoList;
    private List<PlantProtectionDto> plantProtectionDtoList;
    private long userId;

}
