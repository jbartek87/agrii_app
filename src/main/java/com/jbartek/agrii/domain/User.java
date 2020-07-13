package com.jbartek.agrii.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USERS_TABLE")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "FARM_NUMBER")
    private String farmNumber;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @OneToMany(
            targetEntity = Parcel.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Parcel> parcelList = new ArrayList<>();

    @OneToMany(
            targetEntity = Accountancy.class,
            mappedBy = "user",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Accountancy> accountancyList = new ArrayList<>();

    public User(String firstName, String lastName,String farmNumber, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.farmNumber = farmNumber;
        this.email = email;
        this.parcelList = new ArrayList<>();
    }

}

