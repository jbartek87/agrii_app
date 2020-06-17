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
        return new Parcel(
                parcelDto.getId(),
                parcelDto.getParcelNumber(),
                parcelDto.getPrecinct(),
                parcelDto.getSoilType(),
                parcelDto.getArea(),
                fieldWorkService.getAllFieldWork(),
                plantProtectionService.getAllPlantProtection(),
                userService.getUser(parcelDto.getUserId()).orElse(null));

    }

    public ParcelDto mapToParcelDto(final Parcel parcel){
        return new ParcelDto(
                parcel.getId(),
                parcel.getParcelNumber(),
                parcel.getPrecinct(),
                parcel.getSoilType(),
                parcel.getArea(),
                fieldWorkMapper.mapToFieldWorkDtoList(parcel.getFieldWorkList()),
                plantProtectionMapper.mapToPlantProtectionDtoList(parcel.getPlantProtectionList()),
                parcel.getUser().getId());

    }

    public List<ParcelDto> mapToParcelDtoList(final List<Parcel> parcelList) {
        return parcelList.stream()
                .map(p-> new ParcelDto(
                        p.getId(),
                        p.getParcelNumber(),
                        p.getPrecinct(),
                        p.getSoilType(),
                        p.getArea(),
                        fieldWorkMapper.mapToFieldWorkDtoList(p.getFieldWorkList()),
                        plantProtectionMapper.mapToPlantProtectionDtoList(p.getPlantProtectionList()),
                        p.getUser().getId()))
                .collect(Collectors.toList());
    }

    public List<Parcel> mapToParcelList(final List<ParcelDto> parcelDtoList){
        return parcelDtoList.stream()
                .map(p-> new Parcel(
                        p.getId(),
                        p.getParcelNumber(),
                        p.getPrecinct(),
                        p.getSoilType(),
                        p.getArea(),
                        fieldWorkMapper.mapToFieldWorkList(p.getFieldWorkDtoList()),
                        plantProtectionMapper.mapToPlantProtectionList(p.getPlantProtectionDtoList()),
                        userService.getUser(p.getUserId()).orElse(null)))
                .collect(Collectors.toList());

    }

}
