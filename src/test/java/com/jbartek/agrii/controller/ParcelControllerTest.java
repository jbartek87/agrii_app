package com.jbartek.agrii.controller;

import com.google.gson.Gson;
import com.jbartek.agrii.domain.FieldWork;
import com.jbartek.agrii.domain.Parcel;
import com.jbartek.agrii.domain.logs.ApplicationLogs;
import com.jbartek.agrii.dto.FieldWorkDto;
import com.jbartek.agrii.dto.ParcelDto;
import com.jbartek.agrii.dto.PlantProtectionDto;
import com.jbartek.agrii.enums.SoilType;
import com.jbartek.agrii.facade.ParcelFacade;
import com.jbartek.agrii.services.logs.ApplicationLogsService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ParcelController.class)
@RunWith(value = SpringRunner.class)
public class ParcelControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    ParcelFacade facade;

    @MockBean
    ApplicationLogsService service;

//    @Test
//    public void shouldFetchParcelList() throws Exception {
//        //Given
//        List<ParcelDto> parcelDtoList = new ArrayList<>();
//        List<FieldWorkDto> fieldWorkDtoList = new ArrayList<>();
//        List<PlantProtectionDto> plantProtectionDtoList = new ArrayList<>();
//        ParcelDto parcel1 = new ParcelDto(1L, "100", "Babimost", SoilType.GRUNT_ORNY,
//                100.0, fieldWorkDtoList, plantProtectionDtoList, 1L);
//        ParcelDto parcel2 = new ParcelDto(2L, "200", "Kolesin", SoilType.GRUNT_ORNY,
//                100.0, fieldWorkDtoList, plantProtectionDtoList, 1L);
//        parcelDtoList.add(parcel1);
//        parcelDtoList.add(parcel2);
//        when(facade.fetchAllParcels()).thenReturn(parcelDtoList);
//
//        //When & Then
//        mockMvc.perform(MockMvcRequestBuilders.get("/v1/parcels")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)));
//
//    }
//
//    @Test
//    public void shouldFetchParcelById() throws Exception {
//        //Given
//        List<FieldWorkDto> fieldWorkDtoList = new ArrayList<>();
//        List<PlantProtectionDto> plantProtectionDtoList = new ArrayList<>();
//        ParcelDto parcel1 = new ParcelDto(1L, "100", "Babimost", SoilType.GRUNT_ORNY,
//                100.0, fieldWorkDtoList, plantProtectionDtoList, 1L);
//        long parcelId = parcel1.getId();
//        when(facade.fetchParcel(parcelId)).thenReturn(Optional.ofNullable(parcel1));
//        //When & Then
//        mockMvc.perform(MockMvcRequestBuilders.get("/v1/parcels/1")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.parcelNumber", Matchers.is("100")))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.precinct", Matchers.is("Babimost")))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.area", Matchers.is(100.0)))
//                .andExpect(MockMvcResultMatchers.jsonPath("$.userId", Matchers.is(1)));
//    }
//
//    @Test
//    public void shouldCreateParcel() throws Exception {
//        //Given
//        List<FieldWorkDto> fieldWorkDtoList = new ArrayList<>();
//        List<PlantProtectionDto> plantProtectionDtoList = new ArrayList<>();
//        ParcelDto parcel1 = new ParcelDto(1L, "100", "Babimost", SoilType.GRUNT_ORNY,
//                100.0, fieldWorkDtoList, plantProtectionDtoList, 1L);
//        Gson gson = new Gson();
//        String jsonContent = gson.toJson(parcel1);
//
//        //When & Then
//        mockMvc.perform(MockMvcRequestBuilders.post("/v1/parcels")
//                .contentType(MediaType.APPLICATION_JSON)
//                .characterEncoding("UTF-8")
//                .content(jsonContent))
//                .andExpect(status().isOk());
//        Mockito.verify(facade, times(1)).createParcel(any(ParcelDto.class));
//    }
//
//    @Test
//    public void shouldDeleteParcel() throws Exception {
//        //Given
//        List<FieldWorkDto> fieldWorkDtoList = new ArrayList<>();
//        List<PlantProtectionDto> plantProtectionDtoList = new ArrayList<>();
//        ParcelDto parcel1 = new ParcelDto(1L, "100", "Babimost", SoilType.GRUNT_ORNY,
//                100.0, fieldWorkDtoList, plantProtectionDtoList, 1L);
//        long parcelId = parcel1.getId();
//
//        //When & Then
//        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/parcels/1")
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//        Mockito.verify(facade, times(1)).deleteParcel(parcelId);
//    }
//
//    @Test
//    public  void shouldUpdateParcel() throws Exception {
//        //Given
//        List<FieldWorkDto> fieldWorkDtoList = new ArrayList<>();
//        List<PlantProtectionDto> plantProtectionDtoList = new ArrayList<>();
//        ParcelDto parcel1 = new ParcelDto(1L, "100", "Babimost", SoilType.GRUNT_ORNY,
//                100.0, fieldWorkDtoList, plantProtectionDtoList, 1L);
//        Gson gson = new Gson();
//        String jsonContent = gson.toJson(parcel1);
//
//        //When & Then
//        mockMvc.perform(MockMvcRequestBuilders.put("/v1/parcels")
//                .contentType(MediaType.APPLICATION_JSON)
//                .characterEncoding("UTF-8")
//                .content(jsonContent))
//                .andExpect(status().isOk());
//        Mockito.verify(facade, times(2)).updateParcel(any(ParcelDto.class));
//    }

}