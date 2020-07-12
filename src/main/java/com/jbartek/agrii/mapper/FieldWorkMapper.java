package com.jbartek.agrii.mapper;

import com.jbartek.agrii.domain.FieldWork;
import com.jbartek.agrii.dto.FieldWorkDto;
import com.jbartek.agrii.services.ParcelService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FieldWorkMapper {

    @Autowired
    ParcelService service;



    public FieldWork mapToFieldWork(@NotNull final FieldWorkDto fieldWorkDto){
        return FieldWork.builder()
                .id(fieldWorkDto.getId())
                .dateOfWork(fieldWorkDto.getDateOfWork())
                .cultivatedPlant(fieldWorkDto.getCultivatedPlant())
                .typeOfWork(fieldWorkDto.getTypeOfWork())
                .comments(fieldWorkDto.getComments())
                .parcel(service.getParcel(fieldWorkDto.getParcelId()).orElse(null))
                .build();
    }

    public FieldWorkDto mapToFieldWorkDto(final FieldWork fieldWork){
        return new FieldWorkDto(
                fieldWork.getId(),
                fieldWork.getDateOfWork(),
                fieldWork.getCultivatedPlant(),
                fieldWork.getTypeOfWork(),
                fieldWork.getComments(),
                fieldWork.getParcel().getId());
    }

    public List<FieldWorkDto> mapToFieldWorkDtoList(final List<FieldWork> fieldWorkList){
        return fieldWorkList.stream()
                .map(f->new FieldWorkDto(
                        f.getId(),
                        f.getDateOfWork(),
                        f.getCultivatedPlant(),
                        f.getTypeOfWork(),
                        f.getComments(),
                        f.getParcel().getId()))
                .collect(Collectors.toList());
    }

    public List<FieldWork> mapToFieldWorkList(final List<FieldWorkDto> fieldWorkDtoList){
        return fieldWorkDtoList.stream()
                .map(f->new FieldWork(
                        f.getId(),
                        f.getDateOfWork(),
                        f.getCultivatedPlant(),
                        f.getTypeOfWork(),
                        f.getComments(),
                        service.getParcel(f.getParcelId()).orElse(null)))
                .collect(Collectors.toList());
    }

}
