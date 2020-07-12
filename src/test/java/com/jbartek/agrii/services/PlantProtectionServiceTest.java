package com.jbartek.agrii.services;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.jbartek.agrii.domain.Parcel;
import com.jbartek.agrii.domain.PlantProtection;
import com.jbartek.agrii.domain.User;
import com.jbartek.agrii.dto.ParcelDto;
import com.jbartek.agrii.dto.PlantProtectionDto;
import com.jbartek.agrii.dto.UserDto;
import com.jbartek.agrii.enums.ProtectionType;
import com.jbartek.agrii.enums.SoilType;
import com.jbartek.agrii.mapper.ParcelMapper;
import com.jbartek.agrii.mapper.PlantProtectionMapper;
import com.jbartek.agrii.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlantProtectionServiceTest {
    @Autowired
    PlantProtectionService service;

    @Autowired
    UserService userService;

    @Autowired
    PlantProtectionMapper mapper;

    @Autowired
    ParcelService parcelService;

    @Autowired
    ParcelMapper parcelMapper;

    @Autowired
    UserMapper userMapper;

    @Test
    public void testAllPlantProtectionService(){
        UserDto userDto = new UserDto(1L, "Bartek", "Janusz", "PL100", "moj@wp.pl"
                , "12345");
        User user = userService.saveUser(userMapper.mapToUser(userDto));
        ParcelDto parcelDto = new ParcelDto(1L, "100", "Babimost", SoilType.GRUNT_ORNY,
                100, userDto.getId());
        Parcel parcel = parcelService.saveParcel(parcelMapper.mapToParcel(parcelDto));
        PlantProtectionDto plantProtectionDto= new PlantProtectionDto(1L, LocalDate.now(), "Karyx",
                ProtectionType.FUNGICYDE, 10.5, "pszenica", parcel.getId());
        //When
        PlantProtection plantProtection = service.savePlantProtection(mapper.mapToPlantProtection(plantProtectionDto));
        List<PlantProtection> list = service.getAllPlantProtection();
        PlantProtection testedId = service.getPlantProtection(plantProtection.getId()).orElse(null);
        List<PlantProtection> listByEmail = service.getPlantProtectionByEmail(user.getEmail());
        //Then
        Assert.assertEquals(1, list.size());
        Assert.assertEquals("pszenica", testedId.getCultivatedPlant());
        Assert.assertEquals(1, listByEmail.size());
        service.deletePlantProtection(plantProtection.getId());
        parcelService.deleteParcel(parcel.getId());
        userService.deleteUser(user.getId());


    }
}