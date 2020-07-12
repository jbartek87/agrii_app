package com.jbartek.agrii.domain;

import com.jbartek.agrii.enums.TypeOfWork;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "FIELD_WORK")
public class FieldWork {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "ID")
    private long id;

    @Column(name="DATE_OF_WORK")
    private LocalDate dateOfWork;

    @Column(name = "CULTIVATED_PLANT")
    private String cultivatedPlant;

    @Column(name = "TYPE_OF_WORK")
    private TypeOfWork typeOfWork;

    @Column(name = "COMMENTS")
    private String comments;

    @ManyToOne
    private Parcel parcel;


    public FieldWork(LocalDate dateOfWork, String cultivatedPlant, TypeOfWork typeOfWork, String comments, Parcel parcel) {
        this.dateOfWork = dateOfWork;
        this.cultivatedPlant = cultivatedPlant;
        this.typeOfWork = typeOfWork;
        this.comments = comments;
        this.parcel = parcel;
    }
}

