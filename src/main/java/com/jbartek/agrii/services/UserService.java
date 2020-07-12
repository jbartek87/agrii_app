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

    public User getUserByEmail(final String email){return userRepository.findByEmail(email); }

    public boolean validateUser(final String email, final String password){
        Optional<User> user = userRepository.findByEmailAndPassword(email,password);
        if(user.isPresent()){
            return false;
        }else return true;
    }

}
