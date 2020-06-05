package com.jbartek.agrii.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="parcels")
public class Parcel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String parcelNumber;
    private String communeName;
    private SoilType soilType;
    private double area;

    @OneToMany(
            targetEntity = FieldWork.class,
            mappedBy = "parcel",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY

    )
    private List<FieldWork>  fieldWorkList = new ArrayList<>();

}
