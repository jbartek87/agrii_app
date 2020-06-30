package com.jbartek.agrii.services;


import com.jbartek.agrii.domain.User;
import com.jbartek.agrii.dto.UserDto;
import com.jbartek.agrii.mapper.UserMapper;
import com.jbartek.agrii.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper mapper;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Optional<User> getUser(long id){
        return userRepository.findById(id);
    }

    public User saveUser(final User user){
        return userRepository.save(user);
    }

    public void deleteUser(final long id){
        userRepository.deleteById(id);
    }

    public UserDto validateUser(final String email, String firstName){
      return   mapper.mapToUserDto(userRepository.findAllByEmailAndAndFirstName(email, firstName));
    }

}
