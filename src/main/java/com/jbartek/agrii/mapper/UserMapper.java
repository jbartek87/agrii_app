package com.jbartek.agrii.mapper;

import com.jbartek.agrii.domain.User;
import com.jbartek.agrii.dto.UserDto;
import com.jbartek.agrii.services.ParcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    @Autowired
    ParcelService parcelService;

    @Autowired
    ParcelMapper parcelMapper;


    public User mapToUser(final UserDto userDto){
        return User.builder()
                .id(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .farmNumber(userDto.getFarmNumber())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .build();
    }

    public UserDto mapToUserDto(final User user){
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getFarmNumber(),
                user.getEmail(),
                user.getPassword());

    }

    public List<UserDto> mapToUserDtoList(final List<User> userList){
       return userList.stream()
                .map(u->new UserDto(
                        u.getId(),
                        u.getFirstName(),
                        u.getLastName(),
                        u.getFarmNumber(),
                        u.getEmail(),
                        u.getPassword()))
                .collect(Collectors.toList());
    }
}
