package com.jbartek.agrii.repository;

import com.jbartek.agrii.domain.Parcel;
import com.jbartek.agrii.domain.User;
import com.jbartek.agrii.enums.SoilType;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ParcelRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    ParcelRepository parcelRepository;

    @Test
    @Transactional
    public void testRelation(){
        //Given
        User user = new User("Bartek", "Kowalski", "07865456", "bj@bj.com");
        User user2 = new User("Mike", "Sadowski", "098776", "allegro@com.pl");

        Parcel parcelFirst = new Parcel("200", "Babimost", SoilType.GRUNT_ORNY, 100.0);
        Parcel parcelSecond = new Parcel("100", "Kramsk", SoilType.GRUNT_ORNY, 20.0);
        Parcel parcelThird = new Parcel("765", "Krosno", SoilType.GRUNT_ORNY, 12.5);

        user.getParcelList().add(parcelFirst);
        user.getParcelList().add(parcelSecond);
        user2.getParcelList().add(parcelThird);
        parcelFirst.setUser(user);
        parcelSecond.setUser(user);
        parcelThird.setUser(user2);

        //When & Then
        userRepository.save(user);
        userRepository.save(user2);
        parcelRepository.save(parcelFirst);
        parcelRepository.save(parcelThird);
        parcelRepository.save(parcelSecond);

        int result = user.getParcelList().size();
        int result2 = user2.getParcelList().size();

        Assert.assertEquals(2,result);
        Assert.assertEquals(1,result2);

    }
}