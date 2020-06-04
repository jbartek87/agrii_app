package com.jbartek.agrii.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ParcelDto {
    private long id;
    private String parcelNumber;
    private String communeName;
    private SoilType soilType;
    private double area;

}
