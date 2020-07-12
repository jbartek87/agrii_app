package com.jbartek.agrii.services;

import com.jbartek.agrii.domain.Parcel;
import com.jbartek.agrii.repository.ParcelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParcelService {
    @Autowired
    ParcelRepository parcelRepository;

    public List<Parcel> getAllParcels(){
        return parcelRepository.findAll();
    }

    public Optional<Parcel> getParcel(long id){
        return parcelRepository.findById(id);
    }

    public Parcel saveParcel(final Parcel parcel){
        return parcelRepository.save(parcel);
    }

    public void deleteParcel(final long id){
        parcelRepository.deleteById(id);
    }

    public List<Parcel> getParcelByUserEmail(final String email){return parcelRepository.findByUser_Email(email);}

}
