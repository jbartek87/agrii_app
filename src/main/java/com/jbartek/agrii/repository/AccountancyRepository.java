package com.jbartek.agrii.repository;

import com.jbartek.agrii.domain.Accountancy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface AccountancyRepository extends CrudRepository<Accountancy,Long> {
    @Override
    List<Accountancy> findAll();

    Optional<Accountancy> findById(long id);

    @Override
    Accountancy save(Accountancy accountancy);

    void deleteById(Long id);
}