package com.jbartek.agrii.controller;

import com.jbartek.agrii.domain.Parcel;
import com.jbartek.agrii.domain.logs.ApplicationLogs;
import com.jbartek.agrii.dto.ParcelDto;
import com.jbartek.agrii.enums.LogType;
import com.jbartek.agrii.exceptions.ParcelNotFoundException;
import com.jbartek.agrii.facade.ParcelFacade;
import com.jbartek.agrii.mapper.ParcelMapper;
import com.jbartek.agrii.services.ParcelService;
import com.jbartek.agrii.services.logs.ApplicationLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin("*")
public class ParcelController {
    @Autowired
    ParcelFacade facade;

    @Autowired
    ApplicationLogsService service;

    @GetMapping(value = "/parcels")
    public List<ParcelDto> getParcels() {
        return facade.fetchAllParcels();
    }

    @GetMapping(value = "/parcels/{id}")
    public ParcelDto getParcel(@PathVariable Long id) throws ParcelNotFoundException {
        return facade.fetchParcel(id).orElseThrow(ParcelNotFoundException::new);
    }

    @DeleteMapping(value = "/parcels/{id}")
    public void deleteParcel(@PathVariable Long id) {
        ParcelDto tempParcel = facade.fetchParcel(id).orElse(null);
        if(tempParcel!=null){
            service.saveLog(new ApplicationLogs(LogType.DELETED, "Parcel " + tempParcel.getParcelNumber() +
                    " has been deleted"));
        }
        facade.deleteParcel(id);
    }

    @PutMapping(value = "/parcels")
    public ParcelDto updateParcel(@RequestBody ParcelDto parcelDto) {
        ParcelDto tempParcel = facade.updateParcel(parcelDto);
        if(tempParcel!=null){
            service.saveLog(new ApplicationLogs(LogType.UPDATED, "Parcel " + tempParcel.getParcelNumber() +
                    " was updated"));
        }
        return facade.updateParcel(parcelDto);
    }

    @PostMapping(value = "/parcels")
    public void createParcel(@RequestBody ParcelDto parcelDto) {
        facade.createParcel(parcelDto);
        service.saveLog(new ApplicationLogs(LogType.CREATED, "Parcel"  + parcelDto.getParcelNumber() +
                " was created"));
    }
}
