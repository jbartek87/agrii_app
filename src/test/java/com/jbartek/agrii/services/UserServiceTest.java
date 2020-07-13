package com.jbartek.agrii.services;

import com.jbartek.agrii.domain.User;
import com.jbartek.agrii.dto.UserDto;
import com.jbartek.agrii.mapper.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserService service;

    @Autowired
    UserMapper mapper;

    @Test
    public void createUser(){
        //Given
        UserDto userDto = new UserDto();
        userDto.setEmail("wp@wp.pl");
        userDto.setFarmNumber("PL100");
        userDto.setFirstName("Bartek");
        userDto.setLastName("Bill");
        userDto.setPassword("12345");
        //When
        User result = service.saveUser(mapper.mapToUser(userDto));
        long id = result.getId();
        //Then
        Assert.assertNotNull(service.getUser(id));
        service.deleteUser(id);
    }

    @Test
    public void getUserList(){
        //Given
        UserDto userDto = new UserDto();
        userDto.setEmail("wp@wp.pl");
        userDto.setFarmNumber("PL100");
        userDto.setFirstName("Bartek");
        userDto.setLastName("Bill");
        userDto.setPassword("12345");
        User user = service.saveUser(mapper.mapToUser(userDto));
        User userBJ = service.getUser(user.getId()).orElse(null);
        User userJK = service.getUserByEmail("wp@wp.pl");
        //When
        List<User> userList = service.getAllUsers();
        //Then
        Assert.assertEquals(1, userList.size());
        service.deleteUser(user.getId());
    }


    @Test
    public void validateUserTest(){
        //Given
        UserDto userDto = new UserDto();
        userDto.setEmail("wp@wp.pl");
        userDto.setFarmNumber("PL100");
        userDto.setFirstName("Bartek");
        userDto.setLastName("Bill");
        userDto.setPassword("12345");
        User user = service.saveUser(mapper.mapToUser(userDto));
        //When
        boolean result = service.validateUser(user.getEmail(), user.getPassword());
        //Then
        Assert.assertEquals(false, result);
        service.deleteUser(user.getId());
    }


}