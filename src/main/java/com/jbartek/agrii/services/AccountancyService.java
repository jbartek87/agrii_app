package com.jbartek.agrii.services;

import com.jbartek.agrii.domain.Accountancy;
import com.jbartek.agrii.domain.Parcel;
import com.jbartek.agrii.repository.AccountancyRepository;
import com.jbartek.agrii.repository.ParcelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountancyService {

    @Autowired
    AccountancyRepository accountancyRepository;

    public List<Accountancy> getAllAccountancy() {
        return accountancyRepository.findAll();
    }

    public Optional<Accountancy> getAccountancy(long id) {
        return accountancyRepository.findById(id);
    }

    public Accountancy saveAccountancy(final Accountancy accountancy) {
        return accountancyRepository.save(accountancy);
    }

    public void deleteAccountancy(final long id) {
        accountancyRepository.deleteById(id);
    }
}
