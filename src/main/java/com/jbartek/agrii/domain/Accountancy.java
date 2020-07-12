package com.jbartek.agrii.domain;

import com.jbartek.agrii.enums.TypeOfEvent;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "accountancy")
public class Accountancy {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private LocalDate dateOfEvent;
    @Column
    private TypeOfEvent typeOfEvent;
    @Column
    private String invoiceNumber;
    @Column
    private String product;
    @Column
    private int productQuantity;
    @Column
    private BigDecimal netUnitPrice;
    @Column
    private int vatRate;
    @Column
    private BigDecimal netTotalSum;
    @Column
    private BigDecimal totalSum;

    @ManyToOne
    @JoinColumn(name ="USER_ID")
    User user;


}