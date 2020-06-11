package com.jbartek.agrii.dto;

import com.jbartek.agrii.domain.Parcel;
import com.jbartek.agrii.enums.ProtectionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PlantProtectionDto {
    private long id;
    private String productName;
    private ProtectionType protectionType;
    private double dose;
    private String cultivatedPlant;
    private Parcel parcel;
}
