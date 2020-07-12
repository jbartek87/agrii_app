package com.jbartek.agrii.mapper;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.jbartek.agrii.domain.Accountancy;
import com.jbartek.agrii.domain.Parcel;
import com.jbartek.agrii.domain.User;
import com.jbartek.agrii.dto.UserDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {
    @Autowired
    UserMapper mapper;

    @Test
    public void testUserMapper(){
        //Given
        List<Parcel> parcelList = new ArrayList<>();
        List<Accountancy>accountancyList = new ArrayList<>();
        User user = new User(1L, "Bartek", "Kowalski", "PL100", "jb@com.pl",
                "12345",parcelList,accountancyList);
        List<User> userList = new ArrayList<>();
        userList.add(user);
        //When
        UserDto userDto = mapper.mapToUserDto(user);
        User testToUser = mapper.mapToUser(userDto);
        List<UserDto> userDtoList = mapper.mapToUserDtoList(userList);

        //Then
        Assert.assertEquals("jb@com.pl", user.getEmail());
        Assert.assertEquals("12345", testToUser.getPassword());
        Assert.assertNotNull(userDtoList);
    }

}