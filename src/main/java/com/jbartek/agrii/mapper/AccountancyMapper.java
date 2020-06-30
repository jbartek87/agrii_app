package com.jbartek.agrii.mapper;

import com.jbartek.agrii.domain.Accountancy;
import com.jbartek.agrii.dto.AccountancyDto;
import com.jbartek.agrii.services.AccountancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountancyMapper {



    public Accountancy mapToAccountancy(final AccountancyDto accountancyDto){
        return Accountancy.builder()
                .id(accountancyDto.getId())
                .dateOfEvent(accountancyDto.getDateOfEvent())
                .typeOfEvent(accountancyDto.getTypeOfEvent())
                .invoiceNumber(accountancyDto.getInvoiceNumber())
                .product(accountancyDto.getProduct())
                .netUnitPrice(accountancyDto.getNetUnitPrice())
                .productQuantity(accountancyDto.getProductQuantity())
                .vatRate(accountancyDto.getVatRate())
                .netTotalSum(accountancyDto.getNetTotalSum())
                .totalSum(accountancyDto.getTotalSum())
                .build();
    }

    public AccountancyDto mapToAccountancyDto(final Accountancy accountancy) {
        return AccountancyDto.builder()
                .id(accountancy.getId())
                .dateOfEvent(accountancy.getDateOfEvent())
                .typeOfEvent(accountancy.getTypeOfEvent())
                .invoiceNumber(accountancy.getInvoiceNumber())
                .product(accountancy.getProduct())
                .netUnitPrice(accountancy.getNetUnitPrice())
                .productQuantity(accountancy.getProductQuantity())
                .vatRate(accountancy.getVatRate())
                .netTotalSum(accountancy.getNetTotalSum())
                .totalSum(accountancy.getTotalSum())
                .userId(accountancy.getUser().getId())
                .build();
    }

    public List<AccountancyDto> mapToAccountancyDtoList(final List<Accountancy> accountancyList){
        return accountancyList.stream()
                .map(a->new AccountancyDto(
                        a.getId(),
                        a.getDateOfEvent(),
                        a.getTypeOfEvent(),
                        a.getInvoiceNumber(),
                        a.getProduct(),
                        a.getProductQuantity(),
                        a.getNetUnitPrice(),
                        a.getVatRate(),
                        a.getNetTotalSum(),
                        a.getTotalSum(),
                        a.getUser().getId()))
                .collect(Collectors.toList());
    }
}
