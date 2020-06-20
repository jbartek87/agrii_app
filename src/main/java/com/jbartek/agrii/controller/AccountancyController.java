package com.jbartek.agrii.controller;

import com.jbartek.agrii.domain.Accountancy;
import com.jbartek.agrii.domain.logs.ApplicationLogs;
import com.jbartek.agrii.dto.AccountancyDto;
import com.jbartek.agrii.enums.LogType;
import com.jbartek.agrii.exceptions.AccountancyNotFoundException;
import com.jbartek.agrii.facade.AccountancyFacade;
import com.jbartek.agrii.mapper.AccountancyMapper;
import com.jbartek.agrii.services.AccountancyService;
import com.jbartek.agrii.services.logs.ApplicationLogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@CrossOrigin("*")
public class AccountancyController {
    @Autowired
    AccountancyFacade facade;

    @Autowired
    ApplicationLogsService service;

    @GetMapping(value = "/accountancy")
    public List<AccountancyDto> getAllAccountancy() {
        return facade.fetchAllAccountancy();
    }

    @GetMapping(value = "/accountancy/{id}")
    public AccountancyDto getAccountancy(@PathVariable Long accountancyId) throws AccountancyNotFoundException {
        return facade.fetchAccountancy(accountancyId).orElseThrow(AccountancyNotFoundException::new);
    }

    @DeleteMapping(value = "/accountancy/{id}")
    public void deleteAccountancy(@PathVariable Long id) {
        AccountancyDto tempAcc = facade.fetchAccountancy(id).orElse(null);
        if(tempAcc!=null){
            service.saveLog(new ApplicationLogs(LogType.DELETED, "Invoice " + tempAcc.getInvoiceNumber() +
                    " was removed"));
        }
        facade.deleteAccountancy(id);
    }

    @PutMapping(value = "/accountancy")
    public AccountancyDto updateAccountancy(@RequestBody AccountancyDto accountancyDto) {
        AccountancyDto tempAcc = facade.updateAccountancy(accountancyDto);
        if(tempAcc!=null){
            service.saveLog(new ApplicationLogs(LogType.UPDATED, "Invoice " + tempAcc.getInvoiceNumber() +
                    "was updated"));
        }
        return facade.updateAccountancy(accountancyDto);
    }

    @PostMapping(value = "/accountancy")
    public void createAccountancy(@RequestBody AccountancyDto accountancyDto) {
        facade.createAccountancy(accountancyDto);
        service.saveLog(new ApplicationLogs(LogType.DELETED, "Invoice " + accountancyDto.getInvoiceNumber() +
                " was created"));
    }
}
