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

    @Autowired
    AccountancyService service;


    public Accountancy mapToAccountancy(final AccountancyDto accountancyDto){
        return new Accountancy(
                accountancyDto.getId(),
                accountancyDto.getDateOfEvent(),
                accountancyDto.getTypeOfEvent(),
                accountancyDto.getInvoiceNumber(),
                accountancyDto.getProduct(),
                accountancyDto.getProductQuantity(),
                accountancyDto.getNetUnitPrice(),
                accountancyDto.getVatRate(),
                accountancyDto.getNetTotalSum(),
                accountancyDto.getTotalSum());
    }

    public AccountancyDto mapToAccountancyDto(final Accountancy accountancy) {
        return new AccountancyDto(
                accountancy.getId(),
                accountancy.getDateOfEvent(),
                accountancy.getTypeOfEvent(),
                accountancy.getInvoiceNumber(),
                accountancy.getProduct(),
                accountancy.getProductQuantity(),
                accountancy.getNetUnitPrice(),
                accountancy.getVatRate(),
                accountancy.getNetTotalSum(),
                accountancy.getTotalSum());
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
                        a.getTotalSum()))
                .collect(Collectors.toList());
    }
}
