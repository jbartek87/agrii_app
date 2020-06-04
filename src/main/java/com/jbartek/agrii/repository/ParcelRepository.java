package com.jbartek.agrii.repository;

import com.jbartek.agrii.domain.Parcel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ParcelRepository extends CrudRepository<Parcel,Long> {
    @Override
    List<Parcel> findAll();


    Optional<Parcel> findById(long id);

    @Override
    Parcel save(Parcel parcel);

    void deleteById(Long id);
}

