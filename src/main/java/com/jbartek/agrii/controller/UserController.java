package com.jbartek.agrii.controller;


import com.jbartek.agrii.domain.logs.ApplicationLogs;
import com.jbartek.agrii.dto.UserDto;
import com.jbartek.agrii.enums.LogType;
import com.jbartek.agrii.exceptions.UserNotFoundException;
import com.jbartek.agrii.facade.UserFacade;
import com.jbartek.agrii.services.logs.ApplicationLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserFacade facade;

    @Autowired
    ApplicationLogsService service;


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
        UserDto tempUser = facade.updateUser(userDto);
        if(tempUser!=null){
            service.saveLog(new ApplicationLogs(LogType.UPDATED, "User " + tempUser.getLastName() +
                    "was updated"));
        }
        facade.updateUser(userDto);
    }

    @DeleteMapping(value = "/users/{id}")
    public void deleteUser(@PathVariable Long id){
        UserDto tempUser = facade.fetchUser(id).orElse(null);
        if(tempUser!=null){
            service.saveLog(new ApplicationLogs(LogType.DELETED, "User" + tempUser.getLastName() +
                    " was deleted"));
        }
        facade.deleteUser(id);
    }

    @PostMapping( value = "/users")
    public void createUser(@RequestBody UserDto userDto){
        facade.createUser(userDto);
        service.saveLog(new ApplicationLogs(LogType.CREATED, "User" + userDto.getLastName() +
                " was created"));
    }

    @GetMapping(value = "/usersByEmail/{email}")
    public UserDto validateUser(@PathVariable String email){
       return facade.fetchUserByEmail(email);
    }

    @GetMapping(value = "/validateUser/{email}&{password}")
        public boolean validate(@PathVariable String email, @PathVariable String password){
        return facade.validateUser(email,password);
    }

}
