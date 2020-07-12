package com.jbartek.agrii.controller;

import com.jbartek.agrii.domain.FieldWork;
import com.jbartek.agrii.dto.FieldWorkDto;
import com.jbartek.agrii.enums.TypeOfWork;
import com.jbartek.agrii.facade.FieldWorkFacade;
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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(FieldWorkController.class)
@RunWith(value = SpringRunner.class)
public class FieldWorkControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    FieldWorkFacade fieldWorkFacade;

    @MockBean
    ApplicationLogsService applicationLogsService;

    @Test
    public void shouldFetchFieldWorkList()throws  Exception{
        //Given
        List<FieldWorkDto> fieldWorkDtoList = new ArrayList<>();
        FieldWorkDto fieldWorkDto = new FieldWorkDto(1L, LocalDate.now(), "pszenica", TypeOfWork.ORKA,
                "brak", 1L);
        fieldWorkDtoList.add(fieldWorkDto);

        when(fieldWorkFacade.fetchAllFieldWork()).thenReturn(fieldWorkDtoList);

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/fieldWork")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldFetchFieldWorkById() throws Exception {
        //Given
        FieldWorkDto fieldWorkDto = new FieldWorkDto(1L, LocalDate.now(), "pszenica", TypeOfWork.ORKA,
                "brak", 1L);
        long testId = fieldWorkDto.getId();

        when(fieldWorkFacade.fetchFieldWork(testId)).thenReturn(Optional.ofNullable(fieldWorkDto));
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/fieldWork/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cultivatedPlant", Matchers.is("pszenica")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.comments", Matchers.is("brak")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.parcelId", Matchers.is(1)));


    }

    @Test
    public void shouldCreateFieldWork() throws Exception {
        //Given
        FieldWorkDto fieldWorkDto = new FieldWorkDto(1L, LocalDate.now(), "pszenica", TypeOfWork.ORKA,
                "brak", 1L);
        String jsonContent = "{\"id\": \"1\", \"dateOfWork\": \"2020-10-10\", \"cultivatedPlant\": \"pszenica\"," +
                "\"typeOfWork\": \"ORKA\", \"comments\": \"brak\", \"parcelId\": \"1\"}";

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/fieldWork")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
        verify(fieldWorkFacade, times(1)).createFieldWork(any(FieldWorkDto.class));

    }

    @Test
    public void shouldDeleteFieldWork() throws  Exception {
        //Given
        FieldWorkDto fieldWorkDto = new FieldWorkDto(1L, LocalDate.now(), "pszenica", TypeOfWork.ORKA,
                "brak", 1L);
        long testId = fieldWorkDto.getId();
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/fieldWork/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(fieldWorkFacade, times(1)).deleteFieldWork(testId);
    }

    @Test
    public void shouldUpdateFieldWork() throws  Exception {
        //Given
        FieldWorkDto fieldWorkDto = new FieldWorkDto(1L, LocalDate.now(), "pszenica", TypeOfWork.ORKA,
                "brak", 1L);
        String jsonContent = "{\"id\": \"1\", \"dateOfWork\": \"2020-10-10\", \"cultivatedPlant\": \"pszenica\"," +
                "\"typeOfWork\": \"ORKA\", \"comments\": \"brak\", \"parcelId\": \"1\"}";
        when(fieldWorkFacade.updateFieldWork(any(FieldWorkDto.class))).thenReturn(fieldWorkDto);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/fieldWork")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
        verify(fieldWorkFacade, times(2)).updateFieldWork(any(FieldWorkDto.class));
    }

}