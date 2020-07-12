package com.jbartek.agrii.controller;


import com.google.gson.Gson;
import com.jbartek.agrii.domain.Accountancy;
import com.jbartek.agrii.domain.User;
import com.jbartek.agrii.domain.logs.ApplicationLogs;
import com.jbartek.agrii.dto.AccountancyDto;
import com.jbartek.agrii.dto.UserDto;
import com.jbartek.agrii.enums.TypeOfEvent;
import com.jbartek.agrii.facade.AccountancyFacade;
import com.jbartek.agrii.mapper.AccountancyMapper;
import com.jbartek.agrii.services.AccountancyService;
import com.jbartek.agrii.services.logs.ApplicationLogsService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AccountancyController.class)
public class AccountancyControllerTest {

    @MockBean
    AccountancyFacade facade;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    ApplicationLogsService service;

    @MockBean
    AccountancyMapper mapper;

    @MockBean
    AccountancyService accountancyService;



    @Test
    public void shouldFetchAllAccountancy() throws Exception{
        //Given
        List<AccountancyDto> testList = new ArrayList<>();
        AccountancyDto accountancyDto = new AccountancyDto(1L, LocalDate.now(), TypeOfEvent.INCOME,
                "FV100", "pszenica", 100, new BigDecimal(100),
                23, new BigDecimal(100), new BigDecimal(200),1);
        testList.add(accountancyDto);

        when(facade.fetchAllAccountancy()).thenReturn(testList);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/accountancy")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)));

    }

    @Test
    public void shouldFetchAccountancyById() throws Exception {
        //Given
        AccountancyDto accountancyDto = new AccountancyDto(1L, LocalDate.now(), TypeOfEvent.INCOME,
                "FV100", "pszenica", 100, new BigDecimal(100),
                23, new BigDecimal(100), new BigDecimal(200),1);

        when(facade.fetchAccountancy(1L)).thenReturn(Optional.ofNullable(accountancyDto));
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/accountancy/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.typeOfEvent", Matchers.is("INCOME")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.vatRate", Matchers.is(23)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.product", Matchers.is("pszenica")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.invoiceNumber", Matchers.is("FV100")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productQuantity", Matchers.is(100)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.netTotalSum", Matchers.is(100)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalSum", Matchers.is(200)));

    }

    @Test
    public void shouldCreateAccountancy() throws Exception {

        //Given
        AccountancyDto accountancyDto = new AccountancyDto(1L, LocalDate.now(), TypeOfEvent.INCOME,
                "FV100", "pszenica", 100, new BigDecimal(100),
                23, new BigDecimal(100), new BigDecimal(200),1);

        Accountancy accountancy = new Accountancy(1L, LocalDate.now(), TypeOfEvent.INCOME,
                "FV100", "pszenica", 100, new BigDecimal(100),
                23, new BigDecimal(100), new BigDecimal(200), User.builder().build());

        Gson gson = new Gson();
        String jsonContent = "{\"id\": \"1\",\"dateOfEvent\": \"2020-10-10\", \"typeOfEvent\": \"INCOME\", " +
                "\"invoiceNumber\": \"FV100\", \"product\": \"pszenica\", \"productQuantity\": \"100\"," +
                "\"netUnitPrice\": \"100\", \"vatRate\": \"23\", \"netTotalSum\": \"100\"," +
                "\"totalSum\": \"200\", \"userId\": \"1\"}";

        //When
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/accountancy")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
        verify(facade,times(1)).createAccountancy(any(AccountancyDto.class));
    }

    @Test
    public void shouldDeleteAccountancy() throws  Exception{
        //Given
        Accountancy accountancy = new Accountancy(1L, LocalDate.now(), TypeOfEvent.INCOME,
                "FV100", "pszenica", 100, new BigDecimal(100),
                23, new BigDecimal(100), new BigDecimal(200), User.builder().build());
        long accId = accountancy.getId();

        //When & THen
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/accountancy/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(facade, times(1)).deleteAccountancy(accId);
    }

    @Test
    public  void shouldUpdateAccountancy() throws Exception {
        //Given
        AccountancyDto accountancyDto = new AccountancyDto(1L, LocalDate.now(), TypeOfEvent.INCOME,
                "FV100", "pszenica", 100, new BigDecimal(100),
                23, new BigDecimal(100), new BigDecimal(200),1);

        String jsonContent = "{\"id\": \"1\",\"dateOfEvent\": \"2020-10-10\", \"typeOfEvent\": \"INCOME\", " +
                "\"invoiceNumber\": \"FV100\", \"product\": \"pszenica\", \"productQuantity\": \"100\"," +
                "\"netUnitPrice\": \"100\", \"vatRate\": \"23\", \"netTotalSum\": \"100\"," +
                "\"totalSum\": \"200\",\"userId\": \"1\"}";
        when(facade.updateAccountancy(any(AccountancyDto.class))).thenReturn(accountancyDto);
        //When & THen
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/accountancy")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.invoiceNumber", Matchers.is("FV100")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.product", Matchers.is("pszenica")));

    }

//    @Test
//    public void shouldFetchAccountancyByEmail() throws Exception {
//        //Given
//        UserDto userDto = new UserDto(1L, "Bartek", "Kowalski", "PL200987",
//                "jb@google.pl", " 12345");
//        AccountancyDto accountancyDto = new AccountancyDto(1L, LocalDate.now(), TypeOfEvent.INCOME,
//                "FV100", "pszenica", 100, new BigDecimal(100),
//                23, new BigDecimal(100), new BigDecimal(200),1);
//
//        when(facade.fetchAccountancyByEmail("jb@google.pl")).thenReturn(accountancyDto);
//        //When & Then
//        mockMvc.perform(MockMvcRequestBuilders.get("/v1/accountancyByEmail/jb@google.pl")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.typeOfEvent", Matchers.is("INCOME")))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.vatRate", Matchers.is(23)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.product", Matchers.is("pszenica")))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.invoiceNumber", Matchers.is("FV100")))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.productQuantity", Matchers.is(100)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.netTotalSum", Matchers.is(100)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.totalSum", Matchers.is(200)));
//
//    }

}