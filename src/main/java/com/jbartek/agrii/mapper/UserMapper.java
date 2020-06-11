package com.jbartek.agrii.mapper;

import com.jbartek.agrii.domain.User;
import com.jbartek.agrii.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User mapToUser(final UserDto userDto){
        return new User(
                userDto.getId(),
                userDto.getName(),
                userDto.getLastName(),
                userDto.getCity(),
                userDto.getEmail(),
                userDto.getPassword());
    }

    public UserDto mapToUserDto(final User user){
        return new UserDto(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getCity(),
                user.getEmail(),
                user.getPassword());
    }

    public List<UserDto> mapToUserDtoList(final List<User> userList){
       return userList.stream()
                .map(u->new UserDto(
                        u.getId(),
                        u.getLastName(),
                        u.getLastName(),
                        u.getCity(),
                        u.getEmail(),
                        u.getPassword()))
                .collect(Collectors.toList());
    }
}
