package com.jbartek.agrii.mapper;

import com.jbartek.agrii.domain.Parcel;
import com.jbartek.agrii.domain.PlantProtection;
import com.jbartek.agrii.dto.PlantProtectionDto;
import com.jbartek.agrii.enums.ProtectionType;
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
public class PlantProtectionMapperTest {
    @Autowired
    private PlantProtectionMapper mapper;

    @Test
    public void testPlantProtectionMapper(){
        //Given
        Parcel parcel = new Parcel();
        PlantProtection plantProtection = new PlantProtection(1L, LocalDate.now(), "toprex", ProtectionType.FUNGICYDE,
                20.0, "pszenica", parcel);
        List<PlantProtection> plantProtectionList = new ArrayList<>();
        plantProtectionList.add(plantProtection);

        //When
        PlantProtectionDto plantProtectionDto = mapper.mapToPlantProtectionDto(plantProtection);
        PlantProtection testToPlantProt = mapper.mapToPlantProtection(plantProtectionDto);
        List<PlantProtectionDto> protectionDtoList = mapper.mapToPlantProtectionDtoList(plantProtectionList);
        List<PlantProtection> testToProtectionList = mapper.mapToPlantProtectionList(protectionDtoList);

        //Then
        Assert.assertEquals("pszenica", plantProtectionDto.getCultivatedPlant());
        Assert.assertEquals("pszenica", testToPlantProt.getCultivatedPlant());
        Assert.assertNotNull(protectionDtoList);
        Assert.assertNotNull(testToProtectionList);

    }
}