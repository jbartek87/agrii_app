package com.jbartek.agrii.services;

import com.jbartek.agrii.domain.FieldWork;
import com.jbartek.agrii.repository.FieldWorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FieldWorkService {

    @Autowired
    FieldWorkRepository repository;

    public List<FieldWork> getAllFieldWork(){
        return repository.findAll();
    }

    public Optional<FieldWork> getFieldWork(long id){
        return repository.findById(id);
    }

    public FieldWork saveFieldWork(final FieldWork fieldWork){
        return repository.save(fieldWork);
    }

    public void deleteFieldWork(final long id){
        repository.deleteById(id);
    }

}
