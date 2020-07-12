package com.jbartek.agrii.mapper;


import com.jbartek.agrii.domain.Parcel;
import com.jbartek.agrii.dto.ParcelDto;
import com.jbartek.agrii.services.FieldWorkService;
import com.jbartek.agrii.services.PlantProtectionService;
import com.jbartek.agrii.services.UserService;
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

    @Autowired
    PlantProtectionService plantProtectionService;

    @Autowired
    PlantProtectionMapper plantProtectionMapper;

    @Autowired
    UserService userService;

    public Parcel mapToParcel(final ParcelDto parcelDto){
        return Parcel.builder()
                .id(parcelDto.getId())
                .area(parcelDto.getArea())
                .soilType(parcelDto.getSoilType())
                .parcelNumber(parcelDto.getParcelNumber())
                .precinct(parcelDto.getPrecinct())
                .user(userService.getUser(parcelDto.getUserId()).orElse(null))
                .build();

    }

    public ParcelDto mapToParcelDto(final Parcel parcel){
        return  ParcelDto.builder()
                .id(parcel.getId())
                .parcelNumber(parcel.getParcelNumber())
                .precinct(parcel.getPrecinct())
                .area(parcel.getArea())
                .soilType(parcel.getSoilType())
                .userId(parcel.getUser().getId())
                .build();

    }

    public List<ParcelDto> mapToParcelDtoList(final List<Parcel> parcelList) {
        return parcelList.stream()
                .map(p-> new ParcelDto(
                        p.getId(),
                        p.getParcelNumber(),
                        p.getPrecinct(),
                        p.getSoilType(),
                        p.getArea(),
                        p.getUser().getId()))
                .collect(Collectors.toList());
    }


}
