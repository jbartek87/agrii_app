package com.jbartek.agrii.controller;

import com.jbartek.agrii.dto.ParcelDto;
import com.jbartek.agrii.exceptions.ParcelNotFoundException;
import com.jbartek.agrii.mapper.ParcelMapper;
import com.jbartek.agrii.services.ParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin("*")
public class ParcelController {
    @Autowired
    ParcelService service;

    @Autowired
    ParcelMapper parcelMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/parcels")
    List<ParcelDto> getParcels() {
        return parcelMapper.mapToParcelDtoList(service.getAllParcels());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/parcels/{id}")
    public ParcelDto getParcel(@PathVariable Long id) throws ParcelNotFoundException {
        return parcelMapper.mapToParcelDto(service.getParcel(id).orElseThrow(ParcelNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/parcels")
    public void deleteParcel(@PathVariable Long id){
        service.deleteParcel(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/parcels")
    public ParcelDto updateParcel(@RequestBody ParcelDto parcelDto){
        return parcelMapper.mapToParcelDto(service.saveParcel(parcelMapper.mapToParcel(parcelDto)));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/createParcels")
    public void createParcel(@RequestBody ParcelDto parcelDto){
        service.saveParcel(parcelMapper.mapToParcel(parcelDto));
    }
}
