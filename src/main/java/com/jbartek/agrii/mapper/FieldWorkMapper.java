package com.jbartek.agrii.mapper;

import com.jbartek.agrii.domain.FieldWork;
import com.jbartek.agrii.domain.FieldWorkDto;
import com.jbartek.agrii.services.FieldWorkService;
import com.jbartek.agrii.services.ParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FieldWorkMapper {

    @Autowired
    ParcelService parcelService;


    public FieldWork mapToFieldWork(final FieldWorkDto fieldWorkDto){
        return new FieldWork(
                fieldWorkDto.getId(),
                fieldWorkDto.getDateOfWork(),
                parcelService.getParcel(fieldWorkDto.getParcelId()).orElse(null),// tutaj pojawia się konflikt z encją bo wstawiam long parcelId a encja ma Parcel parcel
                fieldWorkDto.getCultivatedPlant(),
                fieldWorkDto.getTypeOfWork(),
                fieldWorkDto.getComments());
    }

    public FieldWorkDto mapToFieldWorkDto(final FieldWork fieldWork){
        return new FieldWorkDto(
                fieldWork.getId(),
                fieldWork.getDateOfWork(),
               parcelService.getParcelId(fieldWork.getParcel().getId()), //  tutaj pojawia się konflikt z encją bo wstawiam long parcelId a encja ma Parcel parcel
                fieldWork.getCultivatedPlant(),
                fieldWork.getTypeOfWork(),
                fieldWork.getComments());
    }

    public List<FieldWorkDto> mapToFieldWorkDtoList(final List<FieldWork> fieldWorkList){
        return fieldWorkList.stream()
                .map(f->new FieldWorkDto(
                        f.getId(),
                        f.getDateOfWork(),
                        parcelService.getParcelId(f.getParcel().getId()),
                        f.getCultivatedPlant(),
                        f.getTypeOfWork(),
                        f.getComments()))
                .collect(Collectors.toList());
    }

}
