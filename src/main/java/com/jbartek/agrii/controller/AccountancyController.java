package com.jbartek.agrii.controller;

import com.jbartek.agrii.domain.AccountancyDto;
import com.jbartek.agrii.exceptions.AccountancyNotFoundException;
import com.jbartek.agrii.mapper.AccountancyMapper;
import com.jbartek.agrii.services.AccountancyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin("*")
public class AccountancyController {
    @Autowired
    AccountancyMapper accountancyMapper;

    @Autowired
    AccountancyService service;

    @RequestMapping(method = RequestMethod.GET, value = "/accountancy")
    List<AccountancyDto> getAllAccountancy() {
        return accountancyMapper.mapToAccountancyDtoList(service.getAllAccountancy());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/accountancy/{id}")
    public AccountancyDto getAccountancy(@PathVariable Long id) throws AccountancyNotFoundException {
        return accountancyMapper.mapToAccountancyDto(service.getAccountancy(id).orElseThrow(AccountancyNotFoundException::new));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/accountancy")
    public AccountancyDto updateAccountancy(@RequestBody AccountancyDto accountancyDto) {
        return accountancyMapper.mapToAccountancyDto(service.saveAccountancy(accountancyMapper.mapToAccountancy(accountancyDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/accountancy/{id}")
    public void deleteAccountancy(@PathVariable Long id){
        service.deleteAccountancy(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/accountancy")
    public void createAccountancy(@RequestBody AccountancyDto accountancyDto){
        service.saveAccountancy(accountancyMapper.mapToAccountancy(accountancyDto));
    }

}
