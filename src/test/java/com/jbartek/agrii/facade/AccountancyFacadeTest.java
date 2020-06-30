package com.jbartek.agrii.facade;

import com.jbartek.agrii.domain.Accountancy;
import com.jbartek.agrii.dto.AccountancyDto;
import com.jbartek.agrii.mapper.AccountancyMapper;
import com.jbartek.agrii.services.AccountancyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class AccountancyFacadeTest {
    @InjectMocks
    private AccountancyFacade facade;

    @Mock
    private AccountancyService service;

    @Mock
    private AccountancyMapper mapper;

    @Test
    public void shouldFetchEmptyList(){
        //Given
        List<AccountancyDto> accountancyDtoList = new ArrayList<>();
    }
}