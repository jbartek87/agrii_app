package com.jbartek.agrii.mapper;

import com.jbartek.agrii.domain.FieldWork;
import com.jbartek.agrii.domain.Parcel;
import com.jbartek.agrii.dto.FieldWorkDto;
import com.jbartek.agrii.enums.TypeOfWork;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class FieldWorkMapperTest {
    @Autowired
    private FieldWorkMapper mapper;

    @Test
    public void testFieldWorkMapper() {
        //Given
        Parcel parcel = new Parcel();
        FieldWork fieldWork = new FieldWork(1L, LocalDate.now(), "pszenica",
                TypeOfWork.ORKA, "brak", parcel);
        List<FieldWork> fieldWorkList = new ArrayList<>();
        fieldWorkList.add(fieldWork);

        //When
        FieldWorkDto fieldWorkDto = mapper.mapToFieldWorkDto(fieldWork);
        FieldWork testToFieldWork = mapper.mapToFieldWork(fieldWorkDto);
        List<FieldWorkDto> testToFieldWorkDtoList = mapper.mapToFieldWorkDtoList(fieldWorkList);
        List<FieldWork> testToFieldWorkList = mapper.mapToFieldWorkList(testToFieldWorkDtoList);

        //Then
        Assert.assertEquals("pszenica", fieldWorkDto.getCultivatedPlant());
        Assert.assertEquals("brak", testToFieldWork.getComments());
        Assert.assertNotNull(testToFieldWorkDtoList);
        Assert.assertNotNull(testToFieldWorkList);
    }

}