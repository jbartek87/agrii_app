package com.jbartek.agrii.repository;

import com.jbartek.agrii.domain.FieldWork;
import com.jbartek.agrii.domain.Parcel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface FieldWorkRepository extends CrudRepository<FieldWork, Long> {

    @Override
    List<FieldWork> findAll();

    Optional<FieldWork> findById(long id);

   @Override
    FieldWork save(FieldWork fieldWork);

    void deleteById(Long id);

    List<FieldWork> findByParcel_UserEmail(String email);

}
