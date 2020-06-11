package com.jbartek.agrii.domain;

import com.jbartek.agrii.enums.ProtectionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PlantProtection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true)
    private long id;

    @Column(name = "DATE_OF_WORK")
    private LocalDate dateOfWork;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "PROTECTION_TYPE")
    private ProtectionType protectionType;

    @Column(name = "DOSE")
    private double dose;

    @Column(name = "CULTIVATED_PLANT")
    private String cultivatedPlant;

    @ManyToOne
    @JoinColumn(name = "PARCEL_ID")
    private Parcel parcel;


}
