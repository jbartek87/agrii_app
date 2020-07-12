package com.jbartek.agrii.facade;

import com.jbartek.agrii.dto.AccountancyDto;
import com.jbartek.agrii.mapper.AccountancyMapper;
import com.jbartek.agrii.repository.UserRepository;
import com.jbartek.agrii.services.AccountancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountancyFacade {

    AccountancyService service;
    UserRepository repository;
    AccountancyMapper mapper;

    @Autowired
    public AccountancyFacade(AccountancyService service, AccountancyMapper accountancyMapper, UserRepository repository) {
        this.service = service;
        this.mapper = accountancyMapper;
        this.repository = repository;
    }

    public List<AccountancyDto> fetchAllAccountancy(){
        return mapper.mapToAccountancyDtoList(service.getAllAccountancy());
    }

    public Optional<AccountancyDto> fetchAccountancy(Long accountancyId){
        return Optional.ofNullable(mapper.mapToAccountancyDto(service.getAccountancy(accountancyId).orElse(null)));

    }

    public void deleteAccountancy(Long accountancyId){
        service.deleteAccountancy(accountancyId);
    }

    public AccountancyDto updateAccountancy(AccountancyDto accountancyDto){
        return mapper.mapToAccountancyDto(service.saveAccountancy(mapper.mapToAccountancy(accountancyDto)));

    }

    public void createAccountancy(AccountancyDto accountancyDto){
        service.saveAccountancy(mapper.mapToAccountancy(accountancyDto));
    }

    public List<AccountancyDto> fetchAccountancyByEmail(String email){
        return mapper.mapToAccountancyDtoList(service.getAccountancyByEmail(email));
    }
}
