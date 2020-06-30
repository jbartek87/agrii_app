package com.jbartek.agrii.controller;

import com.jbartek.agrii.domain.Parcel;
import com.jbartek.agrii.domain.logs.ApplicationLogs;
import com.jbartek.agrii.dto.ParcelDto;
import com.jbartek.agrii.enums.LogType;
import com.jbartek.agrii.exceptions.ParcelNotFoundException;
import com.jbartek.agrii.facade.ParcelFacade;
import com.jbartek.agrii.services.logs.ApplicationLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin("*")
public class ParcelController {
    @Autowired
    ParcelFacade parcelFacade;

    @Autowired
    ApplicationLogsService service;

    @GetMapping(value = "/parcels")
    public List<ParcelDto> getParcels() {
        return parcelFacade.fetchAllParcels();
    }

    @GetMapping(value = "/parcels/{id}")
    public ParcelDto getParcel(@PathVariable Long id) throws ParcelNotFoundException {
        return parcelFacade.fetchParcel(id).orElseThrow(ParcelNotFoundException::new);
    }

    @DeleteMapping(value = "/parcels/{id}")
    public void deleteParcel(@PathVariable Long id) {
        ParcelDto tempParcel = parcelFacade.fetchParcel(id).orElse(null);
        if(tempParcel!=null){
            service.saveLog(new ApplicationLogs(LogType.DELETED, "Parcel " + tempParcel.getParcelNumber() +
                    " has been deleted"));
        }
        parcelFacade.deleteParcel(id);
    }

    @PutMapping(value = "/parcels")
    public ParcelDto updateParcel(@RequestBody ParcelDto parcelDto) {
        ParcelDto tempParcel = parcelFacade.updateParcel(parcelDto);
        if(tempParcel!=null){
            service.saveLog(new ApplicationLogs(LogType.UPDATED, "Parcel " + tempParcel.getParcelNumber() +
                    " was updated"));
        }
        return parcelFacade.updateParcel(parcelDto);
    }

    @PostMapping(value = "/parcels")
    public void createParcel(@RequestBody ParcelDto parcelDto) {
        parcelFacade.createParcel(parcelDto);
        service.saveLog(new ApplicationLogs(LogType.CREATED, "Parcel"  + parcelDto.getParcelNumber() +
                " was created"));
    }

    @GetMapping(value = "/parcelsBy/{email}")
    public List<ParcelDto> getParcelByUserId(@PathVariable String email) {
        return parcelFacade.fetchParcelByUser(email);
    }
}
