package com.jbartek.agrii.mapper;

import com.jbartek.agrii.domain.FieldWork;
import com.jbartek.agrii.domain.Parcel;
import com.jbartek.agrii.domain.PlantProtection;
import com.jbartek.agrii.domain.User;
import com.jbartek.agrii.dto.FieldWorkDto;
import com.jbartek.agrii.dto.ParcelDto;
import com.jbartek.agrii.dto.PlantProtectionDto;
import com.jbartek.agrii.enums.SoilType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ParcelMapperTest {
    @Autowired
    ParcelMapper mapper;

    @Test
    public void testParcelMapper(){
        //Given
        List<FieldWork> fieldWorkList = new ArrayList<>();
        List<PlantProtection> plantProtectionList = new ArrayList<>();
        List<Parcel> parcelList = new ArrayList<>();
        User user = new User();

        Parcel parcel1 = new Parcel(1L, "100", "Babimost", SoilType.GRUNT_ORNY, 100.0, fieldWorkList,
                plantProtectionList, user);
        parcelList.add(parcel1);

        //When
        ParcelDto parcelDto = mapper.mapToParcelDto(parcel1);
        Parcel testToParcel = mapper.mapToParcel(parcelDto);
        List<ParcelDto> testToDtoList = mapper.mapToParcelDtoList(parcelList);
//        List<Parcel> testToParcelList = mapper.mapToParcelList(testToDtoList);

        //Then
        Assert.assertEquals("Babimost", parcel1.getPrecinct());
        Assert.assertEquals("Babimost", testToParcel.getPrecinct());
        Assert.assertNotNull(testToDtoList);
//        Assert.assertNotNull(testToParcelList);
    }
}