package com.jbartek.agrii.facade;

import com.jbartek.agrii.domain.Accountancy;
import com.jbartek.agrii.dto.AccountancyDto;
import com.jbartek.agrii.mapper.AccountancyMapper;
import com.jbartek.agrii.services.AccountancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AccountancyFacade {
    @Autowired
    AccountancyService service;

    @Autowired
    AccountancyMapper mapper;

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
}
