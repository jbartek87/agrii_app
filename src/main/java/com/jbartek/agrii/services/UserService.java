package com.jbartek.agrii.services;


import com.jbartek.agrii.domain.User;
import com.jbartek.agrii.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

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
}