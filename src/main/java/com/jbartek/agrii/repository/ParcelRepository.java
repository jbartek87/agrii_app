package com.jbartek.agrii.repository;

import com.jbartek.agrii.domain.Parcel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ParcelRepository extends CrudRepository<Parcel,Long> {
    @Override
    List<Parcel> findAll();


    Optional<Parcel> findById(long id);

    @Override
    Parcel save(Parcel parcel);

    void deleteById(Long id);


    List<Parcel>findByUser_Email(String email);


}

