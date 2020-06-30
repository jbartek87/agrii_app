package com.jbartek.agrii.facade;


import com.jbartek.agrii.domain.Parcel;
import com.jbartek.agrii.dto.ParcelDto;
import com.jbartek.agrii.dto.UserDto;
import com.jbartek.agrii.mapper.ParcelMapper;
import com.jbartek.agrii.services.ParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ParcelFacade {

    @Autowired
    ParcelService service;

    @Autowired
    ParcelMapper mapper;

    public List<ParcelDto> fetchAllParcels(){
        return mapper.mapToParcelDtoList(service.getAllParcels());
    }

    public Optional<ParcelDto> fetchParcel(Long parcelId){
        return Optional.ofNullable(mapper.mapToParcelDto(service.getParcel(parcelId).orElse(null)));
    }

    public void deleteParcel(Long parcelId){
        service.deleteParcel(parcelId);
    }

    public ParcelDto updateParcel(ParcelDto parcelDto){
        return  mapper.mapToParcelDto(service.saveParcel(mapper.mapToParcel(parcelDto)));
    }

    public void createParcel(ParcelDto parcelDto){
        service.saveParcel(mapper.mapToParcel(parcelDto));
    }

    public List<ParcelDto> fetchParcelByUser(String email){
        return mapper.mapToParcelDtoList(service.getParcelByUserEmail(email));
    }

}
