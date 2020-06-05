package com.jbartek.agrii.repository;

import com.jbartek.agrii.domain.Weather;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface WeatherRepository extends CrudRepository<Weather, Long> {
    @Override
    List<Weather> findAll();


    Optional<Weather> findById(long id);

    @Override
    Weather save(Weather weather);

    void deleteById(Long id);
}
