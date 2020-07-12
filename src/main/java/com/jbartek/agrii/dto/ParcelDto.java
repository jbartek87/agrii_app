package com.jbartek.agrii.dto;

import com.jbartek.agrii.enums.SoilType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParcelDto {
    private long id;
    private String parcelNumber;
    private String precinct;
    private SoilType soilType;
    private double area;
    private long userId;

}
