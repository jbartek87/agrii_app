package com.jbartek.agrii.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "field_work")
public class FieldWork {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private LocalDate dateOfWork;

    @ManyToOne
    @JoinColumn(name = "parcel_id")
    private Parcel parcel;

    private String cultivatedPlant;
    private String typeOfWork;
    private String comments;



}

