package com.jbartek.agrii.domain;

import com.jbartek.agrii.enums.ProtectionType;
import com.sun.istack.NotNull;
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
@Table(name = "PLANT_PROTECTION")
public class PlantProtection {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
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
    @NotNull
    private Parcel parcel;

    public PlantProtection(LocalDate dateOfWork,String productName, ProtectionType protectionType, double dose, String cultivatedPlant, Parcel parcel) {
        this.dateOfWork = dateOfWork;
        this.productName = productName;
        this.protectionType = protectionType;
        this.dose = dose;
        this.cultivatedPlant = cultivatedPlant;
        this.parcel = parcel;
    }
}
