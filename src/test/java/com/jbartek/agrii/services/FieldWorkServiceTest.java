package com.jbartek.agrii.services;

import com.jbartek.agrii.domain.FieldWork;
import com.jbartek.agrii.domain.Parcel;
import com.jbartek.agrii.domain.User;
import com.jbartek.agrii.dto.FieldWorkDto;
import com.jbartek.agrii.dto.ParcelDto;
import com.jbartek.agrii.dto.UserDto;
import com.jbartek.agrii.enums.SoilType;
import com.jbartek.agrii.enums.TypeOfWork;
import com.jbartek.agrii.mapper.FieldWorkMapper;
import com.jbartek.agrii.mapper.ParcelMapper;
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
public class FieldWorkServiceTest {
    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    FieldWorkService fieldWorkService;

    @Autowired
    FieldWorkMapper fieldWorkMapper;

    @Autowired
    ParcelService parcelService;

    @Autowired
    ParcelMapper parcelMapper;

    @Test
    public void testAllFieldWorkService(){
        //Given
        UserDto userDto = new UserDto(1L, "Bartek", "Janusz", "PL100", "moj@wp.pl"
                , "12345");
        User user = userService.saveUser(userMapper.mapToUser(userDto));
        ParcelDto parcelDto = new ParcelDto(1L, "100", "Babimost", SoilType.GRUNT_ORNY,
                100, userDto.getId());
        Parcel parcel = parcelService.saveParcel(parcelMapper.mapToParcel(parcelDto));
        FieldWorkDto fieldWorkDto = new  FieldWorkDto(1L, LocalDate.now(), "rzepak", TypeOfWork.ORKA,
                "brak", parcel.getId());
        FieldWork fieldWork = fieldWorkService.saveFieldWork(fieldWorkMapper.mapToFieldWork(fieldWorkDto));
        //When
        List<FieldWork> fieldWorkList = fieldWorkService.getAllFieldWork();
        FieldWork fieldWorkTestedById = fieldWorkService.getFieldWork(fieldWork.getId()).orElse(null);
        List<FieldWork> fieldWorkListByEmail = fieldWorkService.getFieldWorkByEmail(user.getEmail());
        //Then
        Assert.assertEquals(1, fieldWorkList.size());
        Assert.assertEquals("rzepak", fieldWorkTestedById.getCultivatedPlant());
        Assert.assertEquals(1, fieldWorkListByEmail.size());
        fieldWorkService.deleteFieldWork(fieldWork.getId());
        parcelService.deleteParcel(parcel.getId());
        userService.deleteUser(user.getId());

    }
}