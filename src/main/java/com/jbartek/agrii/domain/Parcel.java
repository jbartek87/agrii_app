package com.jbartek.agrii.domain;

import com.jbartek.agrii.enums.SoilType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PARCEL")
public class Parcel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "PARCEL_NUMBER")
    private String parcelNumber;

    @Column(name = "PRECINCT")
    private String precinct;

    @Column(name = "SOIL_TYPE")
    private SoilType soilType;

    @Column(name = "AREA")
    private double area;

    @OneToMany(
            targetEntity = FieldWork.class,
            mappedBy = "parcel",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY

    )
    private List<FieldWork>  fieldWorkList = new ArrayList<>();

    @OneToMany(
            targetEntity = PlantProtection.class,
            mappedBy = "parcel",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<PlantProtection> plantProtectionList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    public Parcel(String parcelNumber, String precinct, SoilType soilType, Double area) {
        this.parcelNumber = parcelNumber;
        this.precinct = precinct;
        this.soilType = soilType;
        this.area = area;
    }
}
