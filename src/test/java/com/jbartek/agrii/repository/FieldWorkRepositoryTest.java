package com.jbartek.agrii.repository;

import com.jbartek.agrii.domain.FieldWork;
import com.jbartek.agrii.domain.Parcel;
import com.jbartek.agrii.domain.User;
import com.jbartek.agrii.enums.SoilType;
import com.jbartek.agrii.enums.TypeOfWork;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.time.LocalDate;

import static org.junit.Assert.*;

//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class FieldWorkRepositoryTest {
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    ParcelRepository parcelRepository;
//
//    @Autowired
//    FieldWorkRepository fieldWorkRepository;
//
//    @Test
//    @Transactional
//    public void testRealtions(){
//        //Given
//        User user = new User("Bartek", "Kowalski", "07865456", "bj@bj.com");
//        Parcel parcelFirst = new Parcel("200", "Babimost", SoilType.GRUNT_ORNY, 100.0);
//        FieldWork fieldWork1 = new FieldWork(LocalDate.now(), "pszenica", TypeOfWork.ORKA, "brak", parcelFirst);
//        parcelFirst.getFieldWorkList().add(fieldWork1);
//
//        //When & Then
//        userRepository.save(user);
//        parcelRepository.save(parcelFirst);
//        fieldWorkRepository.save(fieldWork1);
//
//        int result2 = parcelFirst.getFieldWorkList().size(); //
//
//        Assert.assertEquals(1, result2);
//    }
//}