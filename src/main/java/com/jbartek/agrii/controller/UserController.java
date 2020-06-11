package com.jbartek.agrii.controller;


import com.jbartek.agrii.dto.UserDto;
import com.jbartek.agrii.exceptions.UserNotFoundException;
import com.jbartek.agrii.mapper.UserMapper;
import com.jbartek.agrii.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserMapper mapper;

    @Autowired
    UserService service;


    @RequestMapping(method = RequestMethod.GET, value = "/users")
    public List<UserDto> getUsers(){
        return mapper.mapToUserDtoList(service.getAllUsers());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
    public UserDto getUser(@PathVariable Long id) throws UserNotFoundException {
        return mapper.mapToUserDto(service.getUser(id).orElseThrow(UserNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/users")
    public void updateUser(@RequestBody UserDto userDto){
        mapper.mapToUserDto(service.saveUser(mapper.mapToUser(userDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/users/{id}")
    public void deleteUser(@PathVariable Long id){
        service.deleteUser(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users")
    public void createUser(@RequestBody UserDto userDto){
        service.saveUser(mapper.mapToUser(userDto));
    }
}
