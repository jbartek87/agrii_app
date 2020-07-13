package com.jbartek.agrii.services;

import com.jbartek.agrii.domain.Accountancy;
import com.jbartek.agrii.domain.User;
import com.jbartek.agrii.dto.AccountancyDto;
import com.jbartek.agrii.dto.UserDto;
import com.jbartek.agrii.enums.TypeOfEvent;
import com.jbartek.agrii.mapper.AccountancyMapper;
import com.jbartek.agrii.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountancyServiceTest {
    @Autowired
    AccountancyService accountancyService;

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    AccountancyMapper accountancyMapper;


    @Test
    public void testFindByEmail(){
        //Given
        UserDto userDto = new UserDto(1L, "Bartek", "Kowalski", "PL200", "wp@wp.pl","12345");


        User userTest = userService.saveUser(userMapper.mapToUser(userDto));
        AccountancyDto accountancy = new AccountancyDto(1L,LocalDate.now(), TypeOfEvent.INCOME,
                "FV100", "pszenica", 100, new BigDecimal(100),
                23, new BigDecimal(100), new BigDecimal(200), userTest.getId());
        Accountancy accountancyTest = accountancyService.saveAccountancy(accountancyMapper.mapToAccountancy(accountancy));
        //When
        List<Accountancy> list = accountancyService.getAccountancyByEmail(userTest.getEmail());
        //Then
        Assert.assertEquals(1, list.size());
        accountancyService.deleteAccountancy(accountancyTest.getId());
        userService.deleteUser(userTest.getId());


    }

    @Test
    public void testFindAccById(){
        //Given
        UserDto userDto = new UserDto(1L, "Bartek", "Kowalski", "PL200", "wp@wp.pl","12345");

        AccountancyDto accountancy = new AccountancyDto(1L,LocalDate.now(), TypeOfEvent.INCOME,
                "FV100", "pszenica", 100, new BigDecimal(100),
                23, new BigDecimal(100), new BigDecimal(200), userDto.getId());
        User userTest = userService.saveUser(userMapper.mapToUser(userDto));
        Accountancy accountancyTest = accountancyService.saveAccountancy(accountancyMapper.mapToAccountancy(accountancy));
        //When
        Accountancy result = accountancyService.getAccountancy(accountancyTest.getId()).orElse(null);
        //Then
        Assert.assertEquals("pszenica", result.getProduct());
        accountancyService.deleteAccountancy(accountancyTest.getId());
        userService.deleteUser(userTest.getId());

    }

}