package com.jbartek.agrii.facade;

import com.jbartek.agrii.dto.UserDto;
import com.jbartek.agrii.mapper.UserMapper;
import com.jbartek.agrii.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import java.util.List;
import java.util.Optional;

@Component
public class UserFacade {
    @Autowired
    UserService service;

    @Autowired
    UserMapper mapper;

    public List<UserDto> fetchUsers(){
        return mapper.mapToUserDtoList(service.getAllUsers());
    }

    public Optional<UserDto> fetchUser(Long userId){
        return Optional.ofNullable(mapper.mapToUserDto(service.getUser(userId).orElse(null)));
    }

    public void deleteUser(long userId) {
        service.deleteUser(userId);
    }

    public UserDto updateUser(UserDto userDto){
        return mapper.mapToUserDto(service.saveUser(mapper.mapToUser(userDto)));
    }

    public void createUser(UserDto userDto){
        service.saveUser(mapper.mapToUser(userDto));
    }
}
