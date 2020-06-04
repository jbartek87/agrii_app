package com.jbartek.agrii.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Accountancy {
    private long id;
    private LocalDate dateOfEvent;
    private TypeOfEvent typeOfEvent;
    private String invoiceNumber;
    private String product;
    private int productQuantity;
    private BigDecimal netUnitPrice;
    private int vatRate;
    private BigDecimal netTotalSum;
    private BigDecimal totalSum;


}