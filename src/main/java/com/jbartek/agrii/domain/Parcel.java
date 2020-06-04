package com.jbartek.agrii.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="parcels")
public class Parcel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="paracel_number")
    private String parcelNumber;

    @Column(name="comune_name")
    private String communeName;

    @Column(name="soil_type")
    private SoilType soilType;

    @Column
    private double area;

}
