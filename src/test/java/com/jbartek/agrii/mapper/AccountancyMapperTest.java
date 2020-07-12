package com.jbartek.agrii.mapper;

import com.jbartek.agrii.domain.Accountancy;
import com.jbartek.agrii.domain.User;
import com.jbartek.agrii.dto.AccountancyDto;
import com.jbartek.agrii.enums.TypeOfEvent;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountancyMapperTest {
    @Autowired
    private AccountancyMapper mapper;

    @Test
    public void testAccountancyMapper(){
        //Given
        Accountancy testCase = new Accountancy(1L, LocalDate.now(), TypeOfEvent.INCOME,
                "FV100", "pszenica", 100, new BigDecimal(100),
                23, new BigDecimal(100), new BigDecimal(200), User.builder().build());
        List<Accountancy> testList = new ArrayList<>();
        testList.add(testCase);
        //When
        AccountancyDto testDto = mapper.mapToAccountancyDto(testCase);
        Accountancy testAcc = mapper.mapToAccountancy(testDto);
        List<AccountancyDto> listDto = mapper.mapToAccountancyDtoList(testList);
        //Then
        Assert.assertEquals("FV100", testDto.getInvoiceNumber());
        Assert.assertEquals("pszenica", testAcc.getProduct());
        Assert.assertEquals(1, listDto.size());

    }

}