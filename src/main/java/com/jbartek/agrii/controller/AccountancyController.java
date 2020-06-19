package com.jbartek.agrii.controller;

import com.jbartek.agrii.domain.Accountancy;
import com.jbartek.agrii.dto.AccountancyDto;
import com.jbartek.agrii.exceptions.AccountancyNotFoundException;
import com.jbartek.agrii.facade.AccountancyFacade;
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
    AccountancyFacade facade;

    @GetMapping(value = "/accountancy")
    public List<AccountancyDto> getAllAccountancy() {
        return facade.fetchAllAccountancy();
    }

    @GetMapping(value = "/accountancy/{id}")
    public AccountancyDto getAccountancy(@PathVariable Long accountancyId) throws AccountancyNotFoundException {
        return facade.fetchAccountancy(accountancyId).orElseThrow(AccountancyNotFoundException::new);
    }

    @DeleteMapping(value = "/accountancy/{accountancyid}")
    public void deleteAccountancy(@PathVariable Long id) {
        facade.deleteAccountancy(id);
    }

    @PutMapping(value = "/accountancy")
    public AccountancyDto updateAccountancy(@RequestBody AccountancyDto accountancyDto) {
        return facade.updateAccountancy(accountancyDto);
    }

    @PostMapping(value = "/accountancy")
    public void createAccountancy(@RequestBody AccountancyDto accountancyDto) {
        facade.createAccountancy(accountancyDto);
    }
}
