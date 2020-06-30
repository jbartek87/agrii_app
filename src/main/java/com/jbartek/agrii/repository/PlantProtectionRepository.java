package com.jbartek.agrii.repository;

import com.jbartek.agrii.domain.PlantProtection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface PlantProtectionRepository extends CrudRepository<PlantProtection, Long> {
    @Override
    List<PlantProtection> findAll();


    Optional<PlantProtection> findById(long id);

    @Override
    PlantProtection save(PlantProtection plantProtection);

    void deleteById(Long id);

    List<PlantProtection> findByParcel_UserEmail(String email);
}
