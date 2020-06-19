package com.jbartek.agrii.controller;


import com.jbartek.agrii.dto.UserDto;
import com.jbartek.agrii.exceptions.UserNotFoundException;
import com.jbartek.agrii.facade.UserFacade;
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
    UserFacade facade;


    @GetMapping( value = "/users")
    public List<UserDto> getUsers(){
        return facade.fetchUsers();
    }

    @GetMapping( value = "/users/{id}")
    public UserDto getUser(@PathVariable Long id) throws UserNotFoundException {
        return facade.fetchUser(id).orElseThrow(UserNotFoundException::new);
    }

    @PutMapping(value = "/users")
    public void updateUser(@RequestBody UserDto userDto){
        facade.updateUser(userDto);
    }

    @DeleteMapping(value = "/users/{id}")
    public void deleteUser(@PathVariable Long id){
        facade.deleteUser(id);
    }

    @PostMapping( value = "/users")
    public void createUser(@RequestBody UserDto userDto){
        facade.createUser(userDto);
    }
}
