package com.jbartek.agrii.mapper;

import com.jbartek.agrii.domain.FieldWork;
import com.jbartek.agrii.domain.Parcel;
import com.jbartek.agrii.dto.ParcelDto;
import com.jbartek.agrii.services.FieldWorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ParcelMapper {

    @Autowired
    FieldWorkService fieldWorkService;

    @Autowired
    FieldWorkMapper fieldWorkMapper;

    public Parcel mapToParcel(final ParcelDto parcelDto){
        return new Parcel(
                parcelDto.getId(),
                parcelDto.getParcelNumber(),
                parcelDto.getCommuneName(),
                parcelDto.getSoilType(),
                parcelDto.getArea(),
                fieldWorkService.getAllFieldWork());
    }

    public ParcelDto mapToParcelDto(final Parcel parcel){
        return new ParcelDto(
                parcel.getId(),
                parcel.getParcelNumber(),
                parcel.getPrecinct(),
                parcel.getSoilType(),
                parcel.getArea(),
                fieldWorkMapper.mapToFieldWorkDtoList(parcel.getFieldWorkList()));
    }

    public List<ParcelDto> mapToParcelDtoList(final List<Parcel> parcelList) {
        return parcelList.stream()
                .map(p-> new ParcelDto(
                        p.getId(),
                        p.getParcelNumber(),
                        p.getPrecinct(),
                        p.getSoilType(),
                        p.getArea(),
                        fieldWorkMapper.mapToFieldWorkDtoList(p.getFieldWorkList())))
                .collect(Collectors.toList());
    }

}
