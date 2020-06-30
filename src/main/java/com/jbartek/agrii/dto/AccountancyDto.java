package com.jbartek.agrii.dto;

import com.jbartek.agrii.enums.TypeOfEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountancyDto {
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
    private long userId;

}
