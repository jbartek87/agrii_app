package com.jbartek.agrii.repository;


import com.jbartek.agrii.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.Optional;

//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class UserRepositoryTest {
//    @Autowired
//    UserRepository userRepository;
//
//    @Test
//    @Transactional
//    public void testUserSave(){
//        //Given
//        User user = new User("John", "Snow", "12345", "jb@sno.pl");
//
//        //When
//        userRepository.save(user);
//
//        //Then
//        long expNumber = user.getId();
//        Optional<User> userResult = userRepository.findById(expNumber);
//        Assert.assertTrue(userResult.isPresent());
//    }
//
//
//}