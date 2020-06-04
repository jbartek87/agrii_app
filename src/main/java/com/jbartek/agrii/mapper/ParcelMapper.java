package com.jbartek.agrii.mapper;

import com.jbartek.agrii.domain.Parcel;
import com.jbartek.agrii.domain.ParcelDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ParcelMapper {
    public Parcel mapToParcel(final ParcelDto parcelDto) {
        return new Parcel(
                parcelDto.getId(),
                parcelDto.getParcelNumber(),
                parcelDto.getCommuneName(),
                parcelDto.getSoilType(),
                parcelDto.getArea());
    }

    public ParcelDto mapToParcelDto(final Parcel parcel) {
        return new ParcelDto(
                parcel.getId(),
                parcel.getParcelNumber(),
                parcel.getCommuneName(),
                parcel.getSoilType(),
                parcel.getArea());
    }

    public List<ParcelDto> mapToParcelDtoList(final List<Parcel> parcelList) {
        return parcelList.stream()
                .map(p -> new ParcelDto(p.getId(), p.getParcelNumber(), p.getCommuneName(), p.getSoilType(), p.getArea()))
                .collect(Collectors.toList());
    }
}
