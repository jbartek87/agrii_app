package com.jbartek.agrii.controller;


import com.jbartek.agrii.domain.PlantProtection;
import com.jbartek.agrii.dto.PlantProtectionDto;
import com.jbartek.agrii.enums.ProtectionType;
import com.jbartek.agrii.facade.PlantProtectionFacade;
import com.jbartek.agrii.services.logs.ApplicationLogsService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PlantProtectionController.class)
@RunWith(SpringRunner.class)
public class PlantProtectionControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PlantProtectionFacade facade;


    @MockBean
    ApplicationLogsService service;

    @Test
    public void shouldFetchAllPlantProtection() throws Exception {
        //Given
        List<PlantProtectionDto> protectionDtoList = new ArrayList<>();
        PlantProtectionDto plantProtectionDto = new PlantProtectionDto(1L, LocalDate.now(), "toprex",
                ProtectionType.FUNGICYDE, 10.5, "rzepak", 1L);
        protectionDtoList.add(plantProtectionDto);
        when(facade.fetchAllPlantProtection()).thenReturn(protectionDtoList);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/plantProtection")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)));

    }

    @Test
    public void shouldFetchPlantProtectionById() throws Exception {
        //Given
        PlantProtectionDto plantProtectionDto = new PlantProtectionDto(1L, LocalDate.now(), "toprex",
                ProtectionType.FUNGICYDE, 10.5, "rzepak", 1L);
        long testId = plantProtectionDto.getId();
        when(facade.fetchPlantProtection(testId)).thenReturn(Optional.ofNullable(plantProtectionDto));
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/plantProtection/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.productName", Matchers.is("toprex")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.dose", Matchers.is(10.5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cultivatedPlant", Matchers.is("rzepak")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.parcelId", Matchers.is(1)));
    }

    @Test
    public void shouldDeletePlantProtection() throws Exception {
        //Given
        PlantProtectionDto plantProtectionDto = new PlantProtectionDto(1L, LocalDate.now(), "toprex",
                ProtectionType.FUNGICYDE, 10.5, "rzepak", 1L);
        long testId = plantProtectionDto.getId();

        //When & Then
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/plantProtection/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(facade, times(1)).deletePlantProtection(testId);

    }

    @Test
    public void shouldCreatePlantProtection() throws Exception {
        //Given
        PlantProtectionDto plantProtectionDto = new PlantProtectionDto(1L, LocalDate.now(), "toprex",
                ProtectionType.FUNGICYDE, 10.5, "rzepak", 1L);
        String jsonContent = "{\"id\": \"1\", \"dateOfWork\": \"2010-10-10\", \"productName\": \"toprex\"," +
                "\"protectionType\": \"FUNGICYDE\", \"dose\": \"10.5\", \"cultivatedPlant\": \"rzepal\"," +
                "\"parcelId\": \"1\"}";
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/plantProtection")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
        verify(facade,times(1)).createPlantProtection(any(PlantProtectionDto.class));

    }

    @Test
    public void shouldUpdatePlantProtection() throws Exception {
        //Given
        PlantProtectionDto plantProtectionDto = new PlantProtectionDto(1L, LocalDate.now(), "toprex",
                ProtectionType.FUNGICYDE, 10.5, "rzepak", 1L);
        String jsonContent = "{\"id\": \"1\", \"dateOfWork\": \"2010-10-10\", \"productName\": \"toprex\"," +
                "\"protectionType\": \"FUNGICYDE\", \"dose\": \"10.5\", \"cultivatedPlant\": \"rzepal\"," +
                "\"parcelId\": \"1\"}";
        when(facade.updatePlantProtection(any(PlantProtectionDto.class))).thenReturn(plantProtectionDto);
        //When & Then
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/plantProtection")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
        verify(facade, times(2)).updatePlantProtection(any(PlantProtectionDto.class));
    }


}